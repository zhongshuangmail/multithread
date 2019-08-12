package com.demo.multithread.thread;

public class ThreadDemo3 extends Thread {
    private int trainCount=100;

    @Override
    public void run() {
        while(trainCount>0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sale();
        }
    }

    private void sale() {
        System.out.println(Thread.currentThread().getName()+"�Ѿ��۳�"+(100-trainCount+1)+"��Ʊ");
        trainCount--;
    }
    
    public static void main(String[] args) {
        ThreadDemo3 demo3=new ThreadDemo3();
        Thread t1=new Thread(demo3,"����һ");
        Thread t2=new Thread(demo3,"���ڶ�");
        t1.start();
        t2.start();
    }
}
