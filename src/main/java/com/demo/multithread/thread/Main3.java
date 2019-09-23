package com.demo.multithread.thread;

public class Main3 {
	public static void main(String[] args) {
		Person person = new Person("4");
		new Thread(new Runnable() {

			@Override
			public void run() {
				person.re12("s");
			}
		}).start();

	}

}

class Person {
	private static final  String b;

	static {
		b="10";
	}
	
	public Person(String a) {
	}

	
	public static void re12(String a) {
	}
}
