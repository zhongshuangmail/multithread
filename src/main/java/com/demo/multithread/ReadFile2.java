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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile2 {
    public static void main(String[] args) {
//	test();
	try {
	    Map<String, String> map = new HashMap<String, String>();
	    addMap(map);
	    // 读取指定文件夹下的所有文件
	    String filepath = "D:\\workspace\\wp1\\wms-biz-config\\wms-biz-config-common\\src\\main\\java\\com\\wumart\\scm\\wms\\bizconfig\\common\\enums\\message";// 给我你的目录文件夹路径

	    extracted(map, filepath);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private static void extracted(Map<String, String> map, String filepath) {
	File file = new File(filepath);
	if (!file.isDirectory()) {
	} else if (file.isDirectory()) {
	    String[] filelist = file.list();

	    for (int i = 0; i < filelist.length; i++) {
		extracted(map, filepath + "\\" + filelist[i]);
		File readfile = new File(filepath + "\\" + filelist[i]);
//		map.forEach((k, v) -> {
		    try {
			FileReader in = new FileReader(readfile);
			BufferedReader bufIn = new BufferedReader(in);
			// 内存流, 作为临时流
			CharArrayWriter tempStream = new CharArrayWriter();
			// 替换
			String line = null;
//			boolean flag=false;
			 String fileName = readfile.getName();
			while ((line = bufIn.readLine()) != null) {
			    Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
			    Matcher m = p.matcher(line);
			    if (m.find() && !line.contains("import")  ) {
//				flag=true;
				
				if(line.contains("private")) {
				    break;
				}
				extracted(line, fileName);
			    }
			    
//			    if (line.contains("return")) {
//				// 替换每行中, 符合条件的字符串
//				// 1.符合替换return Response.writeError(\"新增或者更新商家商品发生异常, 原因: \" ,
//				// ExceptionUtils.getRootCauseMessage(e));
//				if (line.contains(k) && String.valueOf(line.charAt(line.indexOf(k) - 2)).equals("(")
//					&& !line.contains("String.format")
//					&& line.contains("ExceptionUtils.getRootCauseMessage(e)")) {
//				    
//				    //特别注意  必须要用I18nHolder.format(I18nMessageCode.ADD_CUS_EPT_REASON  
//				    //不能后面加上)
//				    line = line.replace("+", ",");
//				    line = line.replace(";", ");");
//				    line = line.replace("\"" + k + "\"", v);
//				} else if (line.contains(k) && line.contains("String.format")) {
//				    line = line.replace(k, v);
//				}else if(line.contains(k)) {
//				    // 2.符合替换 return Response.writeError("新增或者更新商家商品发生异常")
//				    line = line.replace("\"" + k + "\"", v);
//				    line = line.replace( k, v);
//				}
//			    }
//			    // 将该行写入内存
//			    tempStream.write(line);
//			    // 添加换行符
//			    tempStream.append("\n");

			}
//			if(!flag) {
//				System.out.println("skip:"+fileName);
//			    }
//			// 关闭 输入流
//			bufIn.close();
//			// 将内存中的流 写入 文件
//			FileWriter out = new FileWriter(readfile);
//			tempStream.writeTo(out);
//			out.close();
		    } catch (FileNotFoundException e) {
		    } catch (IOException e) {
		    }
//		});
	    }
//	    System.out.println("---------- 所有文件操作完毕 ----------");
	}
    }

    private static void extracted(String line, String fileName) {
	try {
	    StringBuffer sb = new StringBuffer();
	    char chinese = 0;
	    char[] charArray = line.toCharArray();
	    for (int j = 0; j < charArray.length; j++) {
	        chinese = charArray[j];
	        if(isChinese(chinese)) {
	            sb.append(chinese);
	        };
	        
	    }
	    String name = line.substring(0, line.indexOf("(")).trim();
	    String code=fileName.substring(0,fileName.indexOf("."))+"_"+name;
	    System.out.println(code.toLowerCase()+"="+sb);
//				   
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	     e.printStackTrace();
	     System.out.println("error:"+fileName);
	}
    }
    private static void addMap(Map<String, String> map) {
	// 符合替换第一种形式 原型：新增客户发生异常, 原因:%s
//	map.put("新增客户发生异常, 原因: ", "I18nHolder.format(I18nMessageCode.ADD_CUS_EPT_REASON)");
	// 符合替换第二种形式
//	map.put("新增客户发生异常, 原因: %s","I18nHolder.format(I18nMessageCode.ADD_CUS_EPT_REASON)");
//	map.put("授权方ID为空","I18nHolder.format(I18nMessageCode.DATA_QUERY_FAIL_REASON)");
	
	
	
    }
    
    
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
}
