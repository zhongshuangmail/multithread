package com.demo.multithread.thinkforjava;

import java.util.Iterator;

public class PetSequence3 extends PetSequence{
	public Iterator<Integer> iterable(){
		return new Iterator<Integer>() {
			
			private int index=0;
			
			@Override
			public boolean hasNext() {
				return index<ints.length;
			}
			@Override
			public Integer next() {
				return ints[index++];
			}
		};
	}
}

