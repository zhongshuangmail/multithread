package com.demo.multithread
.thread.Future;

import org.springframework.stereotype.Component;
@Component
public class HttpURLHandle extends RequestUrlHandle {
    
    

    @Override
    public synchronized String invoke(RequestResult requestParam,RequestParam param) throws InterruptedException{
    	while(!requestParam.isFlag()) {
            wait();
        }
        return requestParam.getResult();
    }

    @Override
    public synchronized void receive(RequestResult requestParam,RequestParam param)  throws InterruptedException {
       Thread.sleep(1000);
       if(requestParam.isFlag()) {
          return; 
       }
       requestParam.setResult(Thread.currentThread().getName()+"·µ»Ø½á¹û"+param.getParamJson());
       requestParam.setFlag(true);
       notify();
    }

    @Override
    public Protocol protocol() {
        return Protocol.HTTP;
    }
    
}
