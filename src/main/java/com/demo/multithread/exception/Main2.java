package com.demo.multithread.exception;
/**
 * 程序抛错也必须要达到某个状态
 * 可以使用finally
 *
 */
public class Main2 {
	static int count=0;
	/**
	 * count必须满足=2循环才跳出
	 * @param args
	 */
	public static void main(String[] args) {
		while(true) {
			try {
				if(count++==0)
					throw new NullPointerException();
			} catch (NullPointerException e) {
				System.out.println("count = "+count);
			}finally {
				if(count==2) {
					System.out.println("fially count ="+count);
					break;
				}
			}
		}
	}
}
