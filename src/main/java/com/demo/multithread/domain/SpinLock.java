package com.demo.multithread.domain;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    private static Integer j =0;
    
    private  AtomicReference<Thread> cas = new AtomicReference<Thread>();
    public void lock() {
        Thread current = Thread.currentThread();
        // ����CAS
        System.out.println(cas);
        while (!cas.compareAndSet(null, current)) {
            System.out.println("������ѭ��"+current.getName());
        }
        System.out.println(cas);
    }
    public void unlock() {
        Thread current = Thread.currentThread();
        cas.compareAndSet(current, null);
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        SpinLock lock=new SpinLock();
        
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try {
                        j=j+1;
                    }finally{
                        lock.unlock();
                    }
                }
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(j);
       
    }
}