package com.demo.multithread;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ReadFile {
    public static void main(String[] args) {
//	test();
	try {
	    Map<String, String> map = new HashMap<String, String>();
	    addMap(map);
	    // 读取指定文件夹下的所有文件
	    String filepath = "D:\\workspace\\wp1\\wms-master-data\\wms-master-data-service\\src\\main\\java\\com\\wumart\\scm\\wms\\masterdata\\service\\impl";// 给我你的目录文件夹路径

	    extracted(map, filepath);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private static void test() {
	String line = "return Response.writeError(\"新增或者更新商家商品发生异常, 原因: \" , ExceptionUtils.getRootCauseMessage(e)); ";
	String replace = "新增或者更新商家商品发生异常, 原因: ";
	if (line.contains(replace) && String.valueOf(line.charAt(line.indexOf(replace) - 2)).equals("(")
		&& !line.contains("String.format")) {
	    line = line.replace("+", ",");
	    line = line.replace(";", ");");
	    String a = "\"" + replace + "\"";
	    System.out.println(a);
	    line = line.replaceAll("\"" + replace + "\"",
		    "I18nHolder.format(I18nMessageCode.ADD_OR_UPDATE_MER_EPT_REASON)");
	    System.out.println(line);
	}
	
	System.out.println("String.format(\"新增仓位异常，该仓位已经存在\"");
    }

    private static void extracted(Map<String, String> map, String filepath) {
	File file = new File(filepath);
	if (!file.isDirectory()) {
	} else if (file.isDirectory()) {
	    String[] filelist = file.list();

	    for (int i = 0; i < filelist.length; i++) {

		extracted(map, filepath + "\\" + filelist[i]);
		File readfile = new File(filepath + "\\" + filelist[i]);
		map.forEach((k, v) -> {
		    try {
			FileReader in = new FileReader(readfile);
			BufferedReader bufIn = new BufferedReader(in);
			// 内存流, 作为临时流
			CharArrayWriter tempStream = new CharArrayWriter();
			// 替换
			String line = null;
			while ((line = bufIn.readLine()) != null) {
			    if (line.contains("return")) {
				// 替换每行中, 符合条件的字符串
				// 1.符合替换return Response.writeError(\"新增或者更新商家商品发生异常, 原因: \" ,
				// ExceptionUtils.getRootCauseMessage(e));
				if (line.contains(k) && String.valueOf(line.charAt(line.indexOf(k) - 2)).equals("(")
					&& !line.contains("String.format")
					&& line.contains("ExceptionUtils.getRootCauseMessage(e)")) {
				    
				    //特别注意  必须要用I18nHolder.format(I18nMessageCode.ADD_CUS_EPT_REASON  
				    //不能后面加上)
				    line = line.replace("+", ",");
				    line = line.replace(";", ");");
				    line = line.replace("\"" + k + "\"", v);
				} else if (line.contains(k) && line.contains("String.format")) {
				    line = line.replace(k, v);
				}else if(line.contains(k)) {
				    // 2.符合替换 return Response.writeError("新增或者更新商家商品发生异常")
				    line = line.replace("\"" + k + "\"", v);
				    line = line.replace( k, v);
				}
			    }
			    // 将该行写入内存
			    tempStream.write(line);
			    // 添加换行符
			    tempStream.append("\n");

			}
			// 关闭 输入流
			bufIn.close();
			// 将内存中的流 写入 文件
			FileWriter out = new FileWriter(readfile);
			tempStream.writeTo(out);
			out.close();
		    } catch (FileNotFoundException e) {
		    } catch (IOException e) {
		    }
		});
	    }
	    System.out.println("---------- 所有文件操作完毕 ----------");
	}
    }
    private static void addMap(Map<String, String> map) {
	// 符合替换第一种形式 原型：新增客户发生异常, 原因:%s
//	map.put("新增客户发生异常, 原因: ", "I18nHolder.format(I18nMessageCode.ADD_CUS_EPT_REASON)");
	// 符合替换第二种形式
//	map.put("新增客户发生异常, 原因: %s","I18nHolder.format(I18nMessageCode.ADD_CUS_EPT_REASON)");
//	map.put("授权方ID为空","I18nHolder.format(I18nMessageCode.DATA_QUERY_FAIL_REASON)");
	
	
	
    }
	
}
