package com.demo.multithread.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class IterableAndArray {
	
}

class ReversibleArrayList<T> extends ArrayList<T>{
	
	private static final long serialVersionUID = -1946830908064102558L;

	public ReversibleArrayList(Collection<T> c) {
		super(c);
	}
	
	public Iterable<T> reversed(){
		return  new Iterable<T>() {

			@Override
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					
					int current=size()-1;
					
					@Override
					public boolean hasNext() {
						return current>-1;
					}

					@Override
					public T next() {
						return get(current--);
					}
				};
			}
			
		};
	}
	public static void main(String[] args) {
		ReversibleArrayList<Integer> ra=new ReversibleArrayList<Integer>(Arrays.asList(1,3,4,2,5));
		for (Integer i : ra.reversed()) {
			
		}
			
	}
}