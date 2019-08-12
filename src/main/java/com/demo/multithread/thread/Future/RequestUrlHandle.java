package com.demo.multithread.thread.Future;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

public abstract class RequestUrlHandle {
    
    
    
   
    
    private boolean flag=false;
    
    private String result;
    
    public abstract String invoke(RequestParam requestClient) throws InterruptedException;
    
    public abstract void receive() throws InterruptedException;
    
    
    public abstract Protocol protocol();
    
    
    public String request(RequestParam requestClient) throws InterruptedException {
        System.out.println("∑¢ÀÕ«Î«Û");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    receive();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        String invoke = this.invoke(requestClient);
        return invoke;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
}
