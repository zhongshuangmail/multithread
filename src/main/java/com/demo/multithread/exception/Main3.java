package com.demo.multithread.exception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 对于构造阶段可能会抛出异常，并且要求被清理的类
 * 
 *
 */
public class Main3 {
	public static void main(String[] args) {
		try {
			InputFile file=new InputFile("Cleanup.java");
			try {
				//这里代表file创建成功,可以在finally里面关闭它
				String line = file.getLine();
			}catch(Exception e) {
				//代表此对象的调用方法出错
				e.printStackTrace();
			}finally {
				file.dispose();
			}
		} catch (Exception e) {
			
		}
	}
}
class InputFile{
	BufferedReader in = null; 
	
	public InputFile(String file) throws FileNotFoundException {
		try {
			in = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			 // 这里不需要in.close(),因为这里文件并没有打开
			throw e;
		}catch(Exception e) {
			 // 写入reader的时候出现Exception之后，调用 in.close()
			try {
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			throw e;
		}finally {
			 // 不能在finally域 调用in.close(),不然这个对象不能使用
		}
	}
	
	public void dispose() {
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getLine() {
		String s;
		try {
			s=in.readLine();
		}catch(IOException e) {
			throw new RuntimeException("readLine() fail");
		}
		return s;
	}
	
	
}