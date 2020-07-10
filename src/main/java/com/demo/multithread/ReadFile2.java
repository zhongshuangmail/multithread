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
		try {
		    FileReader in = new FileReader(readfile);
		    BufferedReader bufIn = new BufferedReader(in);
		    // 内存流, 作为临时流
		    CharArrayWriter tempStream = new CharArrayWriter();
		    // 替换
		    String line = null;
		    String fileName = readfile.getName();
		    while ((line = bufIn.readLine()) != null) {
			Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
			Matcher m = p.matcher(line);
			if (m.find() && !line.contains("import")) {
			    if (line.contains("private")) {
				break;
			    }
			    extracted(line, fileName);
			}
		    }
		    ;
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	    }
	    System.out.println("---------- 所有文件操作完毕 ----------");
	}
    }

    private static void extracted(String line, String fileName) {
	try {
	    StringBuffer sb = new StringBuffer();
	    char chinese = 0;
	    char[] charArray = line.toCharArray();
	    for (int j = 0; j < charArray.length; j++) {
		chinese = charArray[j];
		if (isChinese(chinese)) {
		    sb.append(chinese);
		}
		;

	    }
	    String name = line.substring(0, line.indexOf("(")).trim();
	    String code = fileName.substring(0, fileName.indexOf(".")) + "_" + name;
	    System.out.println(code.toLowerCase() + "=" + sb);
	} catch (Exception e) {
	    e.printStackTrace();
	    System.out.println("error:" + fileName);
	}
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
