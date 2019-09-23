package com.demo.multithread.domain;

import java.util.concurrent.ForkJoinPool;

public class Main {
	private static String name;

	public Main() {
		System.out.println("f");
	}

	public static void m() {
		System.out.println("d");
	}

	public static void main(String[] args) {
		A a = new B();
	}
}

class A {
	public A() {
		this.a();
	}
	public void a() {
		System.out.println("ab");
	}

}

class B extends A {

	public void a() {
		System.out.println("b");
	}

}
