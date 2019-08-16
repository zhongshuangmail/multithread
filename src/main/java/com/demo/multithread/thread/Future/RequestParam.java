package com.demo.multithread.thread.Future;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class  RequestParam{
	private Protocol protocol;
	
	private String paramJson;
	
	private String result;


	public RequestParam(Protocol protocol, String paramJson) {
		super();
		this.protocol = protocol;
		this.paramJson = paramJson;
	}


	@Override
	public String toString() {
		return "RequestParam [protocol=" + protocol + ", paramJson=" + paramJson + "]";
	}



	
}
