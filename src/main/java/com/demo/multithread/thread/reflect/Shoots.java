package com.demo.multithread.thread.reflect;

public class Shoots extends Better{
	static{
        System.out.println("父类static：Shoots Construct");
    }
	
	Shoots(){
		System.out.println("父类初始化：Shoots");
	}
}
