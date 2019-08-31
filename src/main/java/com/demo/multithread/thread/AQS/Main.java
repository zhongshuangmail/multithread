package com.demo.multithread.thread.AQS;

public class Main {
	
	
	private static MyAQSLock lock=new MyAQSLock();
	
	private static int count;
	
	public static void main(String[] args) {

		Main main=new Main();
		main.a();
		main.b();
		
//		for (int i = 0; i < 10; i++) {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					for (int j = 0; j < 1000; j++) {
//						lock.lock();
//						count++;
//						System.out.println(count);
//						lock.unlock();
//					}
//				}
//			}).start();
//		}
		
	}
	public void a() {
		lock.lock();
		System.out.println("a");
		lock.unlock();
	}
	public void b() {
		lock.lock();
		System.out.println("b");
		lock.unlock();
	}
	
	
}
