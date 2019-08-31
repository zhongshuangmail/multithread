package com.demo.multithread.thread.future;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class  RequestParam{
	private Protocol protocol;
	
	private String paramJson;
	


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
