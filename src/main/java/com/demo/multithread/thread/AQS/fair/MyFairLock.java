package com.demo.multithread.thread.AQS.fair;

import java.util.ArrayList;
import java.util.List;

public class MyFairLock {
	
	private Thread lockedThread=null;
	
	private List<QueueObj> waitQueue=new ArrayList<QueueObj>();
	
	
	final public void lock() throws InterruptedException {
		QueueObj obj=new QueueObj();
		synchronized (this) {
			waitQueue.add(obj);
		}
		try {
			obj.doWait();
			lockedThread=Thread.currentThread();
		} catch (Exception e) {
			synchronized (this) {
				waitQueue.remove(obj);
			}
		}
	}
	
	
	public synchronized void unLock() {
		if(Thread.currentThread()!=lockedThread) {
			throw new IllegalMonitorStateException("Calling thread has not locked this lock");
		}
		lockedThread=null;
		if(waitQueue.size()>0) {
			waitQueue.get(0).doNotify();
		}
	}
	

	
	
}
