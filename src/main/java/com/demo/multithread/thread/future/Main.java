package com.demo.multithread.thread.future;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * futrueģʽ��ѭ���ȴ�������أ���һ���̸߳���д�����ݣ����߳��£���Ҫ���RequestParam����
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
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
