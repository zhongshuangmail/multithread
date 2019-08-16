package com.demo.multithread.thread;

import java.util.concurrent.ExecutionException;

public class ThreadDemo11 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		for (int i = 0; i < 30; i++) {
			new Thread(() -> {
				Singleton sing=Singleton.getInstantce();
				sing.sys();
			}).start();
		}
	}
}

class Singleton {
	//使用volatile 屏蔽指令重排序问题
	private volatile static Singleton singleton;
	public static Singleton getInstantce() {
		
		if (singleton == null) { //1
			synchronized (ThreadDemo11.class) {
				if (singleton == null) {
					//new Singleton()初始化为三个步骤，有可能产生指令重排序问题
					singleton = new Singleton(); //2
//					a. 给 singleton 分配内存
//					b. 调用 Singleton 的构造函数来初始化成员变量
//					c. 将 singleton 对象指向分配的内存空间（执行完这步 singleton 就为非 null 了）
//					如果将b和c的顺序调换，当线程1执行到2的时候，执行c语句，那么singleton内存地址不为null，但是实际对象为空
//					那么线程2刚好执行到1 ，那么会判断不为null，则singleton调用其方法的时候，会报NullException
				}
			}
		}
		return singleton;
	}
	
	public void sys() {
		System.out.println("出来");
	}
}
