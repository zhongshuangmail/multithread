package com.demo.multithread.exception;
/**
 * 重新抛出异常导致之前异常地点丢失
 * @author Administrator
 *
 */
public class Main {
	public static void main(String[] args) {
		try {
			try {
				f();
			} catch (oneException e) {
				/**
				 * 可使用initCause保存原异常信息
				 * twoException twoException = new twoException(null);
				 * twoException.initCause(e);
				 * throw twoException;
				 */
				throw new twoException(null);
				
			}
		} catch (twoException e) {
			e.printStackTrace();
		}
	}
	public static  void f() throws oneException {
		throw new oneException("one");
	}
}	
class  oneException extends Exception{
	oneException(String s){
		super(s);
	}
	}

class  twoException extends Exception{
	twoException(String s){
		super(s);
	}
}
