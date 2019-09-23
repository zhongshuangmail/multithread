package com.demo.multithread.thinkforjava;

import java.util.AbstractCollection;
import java.util.Iterator;

public class PetSequence extends AbstractCollection<Integer>{
	protected Integer[] ints=new Integer[8];

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			
			private int index=0;
			
			@Override
			public Integer next() {
				return ints[index++];
			}
			
			@Override
			public boolean hasNext() {
				return index<ints.length;
			}
		};
	}

	@Override
	public int size() {
		return ints.length;
	}
	
	public static void main(String[] args) {
		PetSequence ps=new PetSequence();
		
	}
}

