package com.demo.multithread.thread;

class ThreadDemo extends Thread {
    public volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("�߳�ִ�п�ʼ");
        while (flag) {

        }
        System.out.println("�߳�ִ�н���");
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}

public class ThreadDemo4 {
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo t1 = new ThreadDemo();
        t1.start();
        Thread.sleep(1000);
        t1.flag = false;
    }
}
