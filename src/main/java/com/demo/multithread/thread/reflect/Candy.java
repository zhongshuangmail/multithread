package com.demo.multithread.thread.reflect;

public class Candy extends Shoots{
	public  static int i =1;
	public final static int j=1;
	
	static {
		System.out.println("子类static：Candy static");
	}
	
	public Candy() {
		System.out.println("子类初始化：Candy construct");
	}
}
