package com.demo.multithread.thread.future;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.stereotype.Component;

import com.demo.multithread.thread.future.CLHLock.CLHNode;

@Component
public abstract class RequestUrlHandle {
	
	BlockingQueue<Thread> queue = new LinkedBlockingDeque<Thread>();

	private static CLHLock lock = new CLHLock();

	ExecutorService fixed = Executors.newFixedThreadPool(4);

	public abstract String invoke(RequestParam param) throws InterruptedException;

	public abstract void receive(RequestParam param) throws InterruptedException;

	public abstract Protocol protocol();

	public String request(RequestParam param) throws InterruptedException, ExecutionException {
		Thread thread=Thread.currentThread();
		CLHNode lockObj = lock.lock();
		fixed.execute(new Runnable() {
			@Override
			public void run() {
				try {
					receive(param);
					queue.put(thread);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		String invoke = extracted(param);
		lock.unLock(lockObj);
		return invoke;
	}

	private String extracted(RequestParam param) throws InterruptedException, ExecutionException {
		while (true) {
			try {
				Thread thread = queue.take();
				if (thread.equals(Thread.currentThread())) {
					return invoke(param);
				}
			} catch (InterruptedException e) {

			}
		}

	}
	
}
