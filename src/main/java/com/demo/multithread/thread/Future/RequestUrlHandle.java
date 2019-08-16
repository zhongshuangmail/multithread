package com.demo.multithread.thread.Future;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.stereotype.Component;
@Component
public abstract class RequestUrlHandle {
	ExecutorService fixed=Executors.newFixedThreadPool(4);
	
	public abstract String invoke(RequestParam param) throws InterruptedException;

	public abstract void receive(RequestParam param) throws InterruptedException;

	public abstract Protocol protocol();

	public String request(RequestParam param) throws InterruptedException {
		MyLock lock=new MyLock(Thread.currentThread());
		System.out.println(Thread.currentThread().getName() + "∑¢ÀÕ«Î«Û" + param);
		fixed.execute(new Runnable() {
			@Override
			public void run() {
				try {
					receive(param);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					try {
						lock.unLock();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		lock.lock();
		String invoke = this.invoke(param);
		return invoke;
	}



}
