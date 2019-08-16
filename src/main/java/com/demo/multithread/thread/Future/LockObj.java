package com.demo.multithread.thread.Future;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LockObj {
	private boolean notify=false;
	
	private Thread currentThread;

	private int count;

	public LockObj(boolean notify, Thread currentThread, int count) {
		super();
		this.notify = notify;
		this.currentThread = currentThread;
		this.count = count;
	}
}
