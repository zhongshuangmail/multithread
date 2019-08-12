package com.demo.multithread.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.StampedLock;

public class StampedLockCUPDemo {
    // 成员变量
    private int x, y;
    
    // 锁实例
    private final StampedLock sl = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        StampedLockCUPDemo demo = new StampedLockCUPDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.distanceFromOrigin();
                }
                AtomicInteger integer=new AtomicInteger(0);
                
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.move(1, 1);
                }
            }).start();
        }
        Thread.sleep(500000);
    }

    // 排它锁-写锁（writeLock）
    void move(int deltaX, int deltaY) {
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
            System.out.println("写线程"+Thread.currentThread().getName()+"    "+"x--" + x + "y--" + y);
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    // 一个只读方法
    // 其中存在乐观读锁到悲观读锁的转换
    double distanceFromOrigin() {

        // 尝试获取乐观读锁
        long stamp = sl.tryOptimisticRead();
        // 将全部变量拷贝到方法体栈内
        double currentX = x, currentY = y;
        // 检查在获取到读锁stamp后，锁有没被其他写线程抢占
        if (!sl.validate(stamp)) {
            System.out.println(Thread.currentThread().getName()+!sl.validate(stamp));
            // 如果被抢占则获取一个共享读锁（悲观获取）
            stamp = sl.readLock();
            try {
                // 将全部变量拷贝到方法体栈内
                currentX = x;
                currentY = y;
                System.out.println(Thread.currentThread().getName()+"写线程侵入：              x--" + x + "y--" + y);
            } finally {
                // 释放共享读锁
                System.out.println(Thread.currentThread().getName()+"释放锁");
                sl.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName()+"线程调用的                             x--"+x+" y--"+y);
        // 这个时候会产生写锁影响，currentX现在不可取
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    // 获取读锁，并尝试转换为写锁
    void moveIfAtOrigin(int newX, int newY) {
        long stamp = sl.tryOptimisticRead();
        try {
            // 如果当前点在原点则移动
            while (x == 0.0 && y == 0.0) {
                // 尝试将获取的读锁升级为写锁
                long ws = sl.tryConvertToWriteLock(stamp);
                // 升级成功，则更新stamp，并设置坐标值，然后退出循环
                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    // 读锁升级写锁失败则释放读锁，显示获取独占写锁，然后循环重试
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            sl.unlock(stamp);
        }
    }
}
