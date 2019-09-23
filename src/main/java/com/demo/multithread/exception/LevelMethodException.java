package com.demo.multithread.exception;

public class LevelMethodException {
	public static void a(){
		try {
			throw new Exception();
		} catch (Exception e) {
			/**
			 * 调用栈，多层级方法
			 */
			for (StackTraceElement trace : e.getStackTrace()) {
				System.out.println(trace.getClassName());
				System.out.println(trace.getMethodName());
			}
			
		}
	}
	static void b() {
		a();
	}
	static void c() {
		b();
	}
	public static void main(String[] args) {
		c();
	}
}	
