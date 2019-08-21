package com.demo.multithread
.thread.Future;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Component
public class HttpURLHandle extends RequestUrlHandle {

	
	
	
	private String result;
	
    @Override
    public  String invoke(RequestParam param) throws InterruptedException{
        return result;
    }

    @Override
    public  void receive(RequestParam param)  throws InterruptedException {
    	setResult(Thread.currentThread().getName()+"·µ»Ø½á¹û"+param.getParamJson());
    }

    @Override
    public Protocol protocol() {
        return Protocol.HTTP;
    }
    
    
}
