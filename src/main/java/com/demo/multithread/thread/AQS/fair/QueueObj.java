package com.demo.multithread.thread.AQS.fair;

public class QueueObj {
	
	private boolean isNotify=false;
	
	public synchronized void doWait() throws InterruptedException {
		while(!isNotify) {
			this.wait();
		}
		this.isNotify=false;
	}
	
	public synchronized void doNotify() {
		this.isNotify=true;
		this.notify();
	}
	
}
