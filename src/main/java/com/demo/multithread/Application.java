package com.demo.multithread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.demo.multithread.thread.Future.Main;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.demo.multithread");
        Main bean = context.getBean(Main.class);
        bean.test();
    }
}
