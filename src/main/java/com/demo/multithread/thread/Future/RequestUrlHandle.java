package com.demo.multithread.thread.Future;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
@Component
public abstract class RequestUrlHandle {

	

	public abstract String invoke(RequestResult requestClient, RequestParam param) throws InterruptedException;

	public abstract void receive(RequestResult requestClient,RequestParam param) throws InterruptedException;

	public abstract Protocol protocol();

	public String request(RequestResult result, RequestParam param) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + "∑¢ÀÕ«Î«Û" + param);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					receive(result,param);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		String invoke = this.invoke(result, param);
		return invoke;
	}


}
