package com.demo.multithread.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadDemo9 {
    public static void main(String[] args) {
        ExecutorService cachePool=Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            int tmp=i;
            cachePool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"�����̳߳�"+tmp);
                }
            });
        }
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        scheduledThreadPool.schedule(new Runnable() {
            
            @Override
            public void run() {
                // TODO Auto-generated method stub
                
            }
        }, 3, TimeUnit.SECONDS);
    }
}
