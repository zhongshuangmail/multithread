package com.demo.multithread.thread;

import java.util.concurrent.ExecutionException;

public class ThreadDemo11 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		for (int i = 0; i < 30; i++) {
			new Thread(() -> {
				Singleton sing=Singleton.getInstantce();
				sing.sys();
			}).start();
		}
	}
}

class Singleton {
	//ʹ��volatile ����ָ������������
	private volatile static Singleton singleton;
	public static Singleton getInstantce() {
		
		if (singleton == null) { //1
			synchronized (ThreadDemo11.class) {
				if (singleton == null) {
					//new Singleton()��ʼ��Ϊ�������裬�п��ܲ���ָ������������
					singleton = new Singleton(); //2
//					a. �� singleton �����ڴ�
//					b. ���� Singleton �Ĺ��캯������ʼ����Ա����
//					c. �� singleton ����ָ�������ڴ�ռ䣨ִ�����ⲽ singleton ��Ϊ�� null �ˣ�
//					�����b��c��˳����������߳�1ִ�е�2��ʱ��ִ��c��䣬��ôsingleton�ڴ��ַ��Ϊnull������ʵ�ʶ���Ϊ��
//					��ô�߳�2�պ�ִ�е�1 ����ô���жϲ�Ϊnull����singleton�����䷽����ʱ�򣬻ᱨNullException
				}
			}
		}
		return singleton;
	}
	
	public void sys() {
		System.out.println("����");
	}
}
