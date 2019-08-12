package com.demo.multithread.thread;

public class ThreadDemo1 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程"+Thread.currentThread().getId());
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("子线程"+i);
                }
                
            }
        }).start();
//        Thread.currentThread().sleep(300000);
        System.out.println("主线程结束");
    }

}

class ThreadDemo2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println(i);
        }
    }

}
