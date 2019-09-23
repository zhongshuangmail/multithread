package com.demo.multithread.thread;

import java.util.concurrent.locks.LockSupport;

public class Main2 {
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				LockSupport.park();
				System.out.println("d");
			}
		});
		thread.start();
		LockSupport.unpark(thread);
	}
}
