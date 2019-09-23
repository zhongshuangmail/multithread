package com.demo.multithread.thread;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityQueueDemo {
	public static void main(String[] args) {
		List<Integer> ints=Arrays.asList(24,23,42,32,134);
		PriorityQueue<Integer> queue =new PriorityQueue<Integer>(ints.size(),new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1<o2) {
					return -1;
				}else if(o1>o2) {
					return 1;
				}
				return 0;
			}
		});
		queue.addAll(ints);
		log(queue.iterator());
	}
	
	
	private static void log(Iterator<Integer> it) {
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
