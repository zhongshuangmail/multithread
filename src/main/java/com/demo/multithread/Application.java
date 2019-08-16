package com.demo.multithread;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.demo.multithread.thread.Future.Main;
import com.demo.multithread.thread.Future.Protocol;
import com.demo.multithread.thread.Future.RequestParam;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.demo.multithread");
        Main bean = context.getBean(Main.class);
        for (int i = 0; i < 5; i++) {
        	int tmp=i;
        	new Thread(()-> {
        		bean.test(new RequestParam(Protocol.HTTP,tmp+""));
        	}).start();        
		}
    }
}
