package com.demo.multithread.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Res {
    
    ThreadLocal local=new ThreadLocal<Integer>(); 
    
    public String userName;

    public String sex;

    public boolean flag = false;

    Lock lock = new ReentrantLock();

}

class Out extends Thread {
    Res res;

    public Out(Res res) {
        this.res = res;
    }

    @Override
    public void run() {

        int count = 0;
        while (true) {
            try {
                Condition newCondition = res.lock.newCondition();
                res.lock.lock();
                newCondition.await();
                newCondition.signal();
                if (count == 0) {
                    res.userName = "Ð¡ºì";
                    res.sex = "Å®";
                } else {
                    res.userName = "Ð¡ÄÐ";
                    res.sex = "ÄÐ";
                }
                count = (count + 1) % 2;
                res.flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                res.lock.unlock();
            }
        }
    }
}

class Input extends Thread {
    Res res;

    public Input(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            try {
                res.lock.lock();
                System.out.println(res.userName + "   " + res.sex);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally {
                res.lock.unlock();
            }
        }
    }
}

public class ThreadDemo7 {
    public static void main(String[] args) {
        Res res = new Res();
        Out out = new Out(res);
        Input input = new Input(res);
        out.start();
        input.start();
    }
}
