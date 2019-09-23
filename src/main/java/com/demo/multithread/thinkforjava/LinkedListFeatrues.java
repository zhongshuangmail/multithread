package com.demo.multithread.thinkforjava;

import java.util.LinkedList;

public class LinkedListFeatrues {
	public static void main(String[] args) {
		LinkedList<Integer> nums=new LinkedList<Integer>();
		nums.add(1);
		nums.add(2);
		nums.add(3);
		nums.add(4);
		nums.add(5);
		System.out.println(nums);
		//返回第一个元素不移除
		System.out.println(nums.getFirst());
		System.out.println(nums.element());
		//返回第一个元素，如果没有则为空
		System.out.println(nums.peek());
		//移除一个元素并返回
//		System.out.println(nums.poll());
		
		nums.add(nums.size()/2,0);
		
		System.out.println(nums);
		
	}
}
