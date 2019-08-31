package com.demo.multithread;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.demo.multithread.thread.future.Main;
import com.demo.multithread.thread.future.Protocol;
import com.demo.multithread.thread.future.RequestParam;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.demo.multithread");
        Main bean = context.getBean(Main.class);
        for (int i = 0; i < 2; i++) {
        	int tmp=i;
        	new Thread(()-> {
        		bean.test(new RequestParam(Protocol.HTTP,tmp+""));
        	}).start();        
		}
    }
}
