package com.demo.multithread.exception;

import java.util.logging.Logger;


public class LoggingException extends Exception {
	private static Logger logger =Logger.getLogger("Logger");
	private Integer exceptionNum;

	public LoggingException(String msg,Integer exceptionNum) {
		super(msg);
		this.exceptionNum=exceptionNum;
	}
	
	public int getExceptionNum() {
		return exceptionNum;
	}
	
	public String getMessage() {
		return "exceptionMsg:"+super.getMessage();
	}

	public static void main(String[] args) {
		try {
			throw new LoggingException("错误信息", 24);
		} catch (LoggingException e) {
			logger.info(e.getMessage());
			logger.info(""+e.getExceptionNum());
		}
	}
	
}

