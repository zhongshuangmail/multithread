package com.demo.multithread.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadDemo10 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService cachePool=Executors.newCachedThreadPool();
        Future<String> submit = cachePool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(10000);
                System.out.println("123");
                return "String";
            }
        });
        System.out.println("������һ���߳��첽������һ���̵߳ķ��ؽ��");
        cachePool.execute(new Runnable() {
            
            @Override
            public void run() {
                try {
                    if(submit.get().equals("String")) {
                        //�����߼���������������ݿ���Ϣ������ǰ����ʾ
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
