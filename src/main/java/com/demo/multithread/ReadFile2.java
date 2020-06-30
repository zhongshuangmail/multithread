package com.demo.multithread;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadFile2 {
	public static void main(String[] args) throws IOException {
		//原有的内容
		String srcStr = "WUMART";        
		//要替换的内容
        String replaceStr = "物美";     
        // 读  
        File file = new File("D:\\workspace\\wp1\\wms-web\\src\\main\\java\\com\\wumart\\scm\\wms\\web\\controller\\api\\OfcOrderController.java");   
        FileReader in = new FileReader(file);  
        BufferedReader bufIn = new BufferedReader(in);  
        // 内存流, 作为临时流  
        CharArrayWriter  tempStream = new CharArrayWriter();  
        // 替换  
        String line = null;  
        while ( (line = bufIn.readLine()) != null) {  
            // 替换每行中, 符合条件的字符串  
            line = line.replaceAll(srcStr, replaceStr);  
            // 将该行写入内存  
            tempStream.write(line);  
            // 添加换行符  
            tempStream.append(System.getProperty("line.separator"));  
        }  
        // 关闭 输入流  
        bufIn.close();  
        // 将内存中的流 写入 文件  
        FileWriter out = new FileWriter(file);  
        tempStream.writeTo(out);  
        out.close(); 
	}
}
