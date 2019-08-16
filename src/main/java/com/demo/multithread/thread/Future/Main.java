package com.demo.multithread.thread.Future;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * futrue模式，循环等待结果返回，另一个线程负责写入数据，多线程下，需要多个RequestParam参数
 * 
 * @author Administrator
 *
 */
@Component
public class Main {
	Map<Protocol, RequestUrlHandle> map = new ConcurrentHashMap<Protocol, RequestUrlHandle>();

	public Main(ObjectProvider<RequestUrlHandle[]> objectProvider) {
		RequestUrlHandle[] loginFactories = objectProvider.getIfAvailable();
		if (!CollectionUtils.isEmpty(Arrays.asList(loginFactories))) {
			map.putAll(Arrays.stream(loginFactories).collect(Collectors.toMap(k -> k.protocol(), v -> v)));
		}
	}

	public void test(RequestParam param) {
		RequestUrlHandle requestUrlHandle = map.get(param.getProtocol());
		try {
			String request = requestUrlHandle.request(param);
			System.out.println(request);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
