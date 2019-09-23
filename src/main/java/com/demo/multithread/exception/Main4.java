package com.demo.multithread.exception;

public class Main4 {
	public static void main(String[] args) {
		try {
			g();
		} catch (RuntimeException e) {
			try {
				throw e.getCause();
			} catch (ArrayIndexOutOfBoundsException e1) {
				e1.printStackTrace();
			}catch(ArithmeticException e2){
				e2.printStackTrace();
			} catch (Throwable e3) {
				e3.printStackTrace();
			}
		}
	}
	public static void g() {
		try {

			f();
		} catch (Exception e) {
			throw  new RuntimeException(e);
		}
	}
	public static void e() {
		try {
			int[] a=new int[1];
			a[1]=2;
		} catch (Exception e1) {
			throw  new RuntimeException(e1);
		}
	}

	public static void f() {
		try {
			e();
			int i =1/0;
		} catch (Exception e) {
			throw  new RuntimeException(e);
		}
	}
}


