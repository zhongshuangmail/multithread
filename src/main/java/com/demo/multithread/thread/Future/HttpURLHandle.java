package com.demo.multithread
.thread.Future;

import org.springframework.stereotype.Component;
@Component
public class HttpURLHandle extends RequestUrlHandle {

    @Override
    public synchronized String invoke(RequestParam param) throws InterruptedException{
        return  param.getResult();
    }

    @Override
    public synchronized void receive(RequestParam param)  throws InterruptedException {
    	param.setResult(Thread.currentThread().getName()+"·µ»Ø½á¹û"+param.getParamJson());
    }

    @Override
    public Protocol protocol() {
        return Protocol.HTTP;
    }
    
}
