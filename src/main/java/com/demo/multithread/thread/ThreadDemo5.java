package com.demo.multithread.thread;

import java.util.concurrent.atomic.AtomicInteger;

class ThreadDemo0 extends Thread {
    
    private static AtomicInteger count=new AtomicInteger(0);
    
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            count.incrementAndGet();
        }
        System.out.println(Thread.currentThread().getName()+"::"+count.get());
    }


}

public class ThreadDemo5{
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo0[] demo=new ThreadDemo0[10];
        
        for (int i = 0; i < 10; i++) {
            demo[i]=new ThreadDemo0();
        }
        
        for (int i = 0; i < demo.length; i++) {
            demo[i].start();
        }
        
        
    }
}
