package com.demo.multithread.thread.Future;

public class RequestResult {

	private boolean flag;

	private String result;

	public RequestResult() {
		super();
	}

	public RequestResult(boolean flag, String result) {
		super();
		this.flag = flag;
		this.result = result;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
