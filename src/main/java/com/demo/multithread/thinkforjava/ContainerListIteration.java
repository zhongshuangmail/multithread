package com.demo.multithread.thinkforjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ContainerListIteration {
	public static void main(String[] args) {
		List<Integer> empty = Arrays.asList(1,3,24);
		ArrayList<Integer> pets=new ArrayList<Integer>(empty);
		//初始指针指到1
		ListIterator<Integer> lt = pets.listIterator(1);
		while(lt.hasNext()) {
			//指针后移一位
			System.out.println("cutList:"+lt.next());
			
			//指针前一位的位置
			System.out.println("preNodeIndex:"+lt.previousIndex());
		}
		while(lt.hasPrevious()) {
			//指针前移一位并返回元素
			System.out.println("preNode:"+lt.previous());
		}
		System.out.println(lt.next());
	}
}
