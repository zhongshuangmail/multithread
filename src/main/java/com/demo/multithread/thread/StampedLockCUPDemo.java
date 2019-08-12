package com.demo.multithread.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.StampedLock;

public class StampedLockCUPDemo {
    // ��Ա����
    private int x, y;
    
    // ��ʵ��
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

    // ������-д����writeLock��
    void move(int deltaX, int deltaY) {
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
            System.out.println("д�߳�"+Thread.currentThread().getName()+"    "+"x--" + x + "y--" + y);
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    // һ��ֻ������
    // ���д����ֹ۶��������۶�����ת��
    double distanceFromOrigin() {

        // ���Ի�ȡ�ֹ۶���
        long stamp = sl.tryOptimisticRead();
        // ��ȫ������������������ջ��
        double currentX = x, currentY = y;
        // ����ڻ�ȡ������stamp������û������д�߳���ռ
        if (!sl.validate(stamp)) {
            System.out.println(Thread.currentThread().getName()+!sl.validate(stamp));
            // �������ռ���ȡһ��������������ۻ�ȡ��
            stamp = sl.readLock();
            try {
                // ��ȫ������������������ջ��
                currentX = x;
                currentY = y;
                System.out.println(Thread.currentThread().getName()+"д�߳����룺              x--" + x + "y--" + y);
            } finally {
                // �ͷŹ������
                System.out.println(Thread.currentThread().getName()+"�ͷ���");
                sl.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName()+"�̵߳��õ�                             x--"+x+" y--"+y);
        // ���ʱ������д��Ӱ�죬currentX���ڲ���ȡ
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    // ��ȡ������������ת��Ϊд��
    void moveIfAtOrigin(int newX, int newY) {
        long stamp = sl.tryOptimisticRead();
        try {
            // �����ǰ����ԭ�����ƶ�
            while (x == 0.0 && y == 0.0) {
                // ���Խ���ȡ�Ķ�������Ϊд��
                long ws = sl.tryConvertToWriteLock(stamp);
                // �����ɹ��������stamp������������ֵ��Ȼ���˳�ѭ��
                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    // ��������д��ʧ�����ͷŶ�������ʾ��ȡ��ռд����Ȼ��ѭ������
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            sl.unlock(stamp);
        }
    }
}
