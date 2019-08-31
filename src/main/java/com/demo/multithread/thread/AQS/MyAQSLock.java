package com.demo.multithread.thread.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyAQSLock implements Lock{

	private final MySync mySync;
	
	public MyAQSLock() {
		this.mySync = new NonFairSync();
	}

	private static abstract class MySync extends AbstractQueuedSynchronizer{
		
		private static final long serialVersionUID = 7942180218665161689L;

		final boolean nonfairTryAcquire(int acquires) {
			Thread curernt=Thread.currentThread();
			int c =getState();
			if(c==0 && compareAndSetState(0,acquires)) {
				setExclusiveOwnerThread(curernt);
				return true;
			}else if(curernt==getExclusiveOwnerThread()) {
				int nextc=c+acquires;
				if(nextc<0) {
					throw new  Error("Maximum lock count exceeded");
				}
				setState(nextc);
			    return true;
			}
			return false;
		}
		
		
		
		
		@Override
		protected boolean tryRelease(int arg) {
			Thread currentThread = Thread.currentThread();
			int state = getState()-arg;
			if(currentThread!=getExclusiveOwnerThread()) {
				throw new IllegalMonitorStateException();
			}
			boolean flag=false;
			if(state==0) {
				setExclusiveOwnerThread(null);
				flag=true;
			}
			setState(state);
			return flag;
		}




		final ConditionObject getCondition() {
			return new ConditionObject();
		}
	}
	
	private static class NonFairSync extends MySync{

		private static final long serialVersionUID = 4787840520521159526L;

		@Override
		protected boolean tryAcquire(int arg) {
			return nonfairTryAcquire(arg);
		}
		
	}
	
	
	@Override
	public void lock() {
		mySync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		mySync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return mySync.nonfairTryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return mySync.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		mySync.release(1);
	}

	@Override
	public Condition newCondition() {
		return mySync.getCondition();
	}
	
	
}
