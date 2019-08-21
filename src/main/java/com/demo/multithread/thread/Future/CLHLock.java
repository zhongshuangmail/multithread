package com.demo.multithread.thread.Future;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CLHLock {

	private volatile CLHNode tail;

	private static final ThreadLocal<CLHNode> LOCAL = new ThreadLocal<CLHNode>();

	private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATE = AtomicReferenceFieldUpdater
			.newUpdater(CLHLock.class, CLHNode.class, "tail");

	public CLHNode lock() throws InterruptedException {
		CLHNode node = new CLHNode();
//		LOCAL.set(node);
		CLHNode preNode = UPDATE.getAndSet(this, node);
		System.out.println(Thread.currentThread().getName()+"CLHNode"+preNode);
		if (preNode != null) {
			while (preNode.isLocked) {
			}
			preNode = null;
			LOCAL.set(node);
		}
		return node;
	}

	public void unLock(CLHNode node) throws InterruptedException {
//		CLHNode node = LOCAL.get();
		if (!UPDATE.compareAndSet(this, node, null)) {
			node.isLocked = false;
		}
		node = null;
	}

	public static class CLHNode {
		private volatile boolean isLocked = true;

	}
//	BlockingQueue<Thread> queue = new  LinkedBlockingDeque<Thread>();
//	public  void lock() throws InterruptedException {
//		while (true) {
//			Thread peek = queue.take();
//			if(peek.equals(currentThread)) {
//				break;
//			}
//		}
//		
//	}
//
//	public  void unLock() throws InterruptedException {
//		queue.put(currentThread);
//	}

}
