package com.demo.multithread.thread.Future;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyLock {

	BlockingQueue<Thread> queue = new  LinkedBlockingDeque<Thread>();

	private Thread currentThread;

	private int count;

	public MyLock() {
		super();
	}

	public MyLock(Thread currentThread) {
		super();
		this.currentThread = currentThread;
	}

	public  void lock() throws InterruptedException {
		while (true) {
			Thread peek = queue.take();
			if(peek.equals(currentThread)) {
				break;
			}
		}
		
	}

	public  void unLock() throws InterruptedException {
		queue.put(currentThread);
	}

}
