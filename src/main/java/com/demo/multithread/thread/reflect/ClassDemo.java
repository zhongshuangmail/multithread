package com.demo.multithread.thread.reflect;
/**
 * Class.forName() 
 * 可加载.java,会执行类中的static及static变量
 */
public class ClassDemo {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Integer i=Candy.i;
		System.out.println("-----------------");
		int j = Candy.j;
	}
}

