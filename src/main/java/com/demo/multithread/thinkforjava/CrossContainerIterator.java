package com.demo.multithread.thinkforjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class CrossContainerIterator {
	
	public static void display(Iterator<Integer> it) {
		while(it.hasNext()) {
			Integer next = it.next();
			System.out.println("numvalue:"+next);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		List<Integer> empty = Arrays.asList(1,3,24);
		ArrayList<Integer> pets=new ArrayList<Integer>(empty);
		HashSet<Integer> petsHs=new HashSet<Integer>(empty);
		LinkedList<Integer> petsLL = new LinkedList<Integer>(empty);
		TreeSet<Integer> petsTs=new TreeSet<Integer>(empty);
		display(pets.iterator());
		display(petsHs.iterator());
		display(petsLL.iterator());
		display(petsTs.iterator());
	}
}
