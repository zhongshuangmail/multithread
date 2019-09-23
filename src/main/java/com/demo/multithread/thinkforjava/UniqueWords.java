package com.demo.multithread.thinkforjava;

import java.util.Comparator;
import java.util.PriorityQueue;

public class UniqueWords {
	public static void main(String[] args) {
		PriorityQueue<Integer> queue =new PriorityQueue<Integer>();
		queue.add(3);
		queue.add(4);
		queue=new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
	}
}
