package com.demo.multithread.forkjoin;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class ForkJoinDemo<T,V> extends RecursiveTask<V> {

	private final int MEDIAN_NUM = 5;
	
	private List<T> t;
	
	@SuppressWarnings("unchecked")
	public ForkJoinDemo() {
		init((Class<T>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]));
	}
	private void init(Class<T> clazz) {
		Field[] fields = clazz.getFields();
		
		
	}
	public ForkJoinDemo(List<T> orders) {
		this.t=orders;
	}
	@Override
	protected V compute() {
		for (int i = 0; i < t.size(); i++) {
			T t2 = t.get(i);
			
		}
		
		if(t.size()<MEDIAN_NUM) {
			return calc(t);
		}else {
			 ForkJoinDemo<T,V> demo=new ForkJoinDemo<T, V>();
		}
		return null;
	}

	private V calc(List<T> t2) {
		
		
		return null;
	}

}
