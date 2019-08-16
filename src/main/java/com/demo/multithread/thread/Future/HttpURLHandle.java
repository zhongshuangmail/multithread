package com.demo.multithread
.thread.Future;

import org.springframework.stereotype.Component;
@Component
public class HttpURLHandle extends RequestUrlHandle {
    
    

    @Override
    public synchronized String invoke(RequestResult result,RequestParam param) throws InterruptedException{
    	while(!result.isFlag()) {
            wait();
        }
        return result.getResult();
    }

    @Override
    public synchronized void receive(RequestResult result,RequestParam param)  throws InterruptedException {
       Thread.sleep(1000);
       if(result.isFlag()) {
          return; 
       }
       result.setResult(Thread.currentThread().getName()+"·µ»Ø½á¹û"+param.getParamJson());
       result.setFlag(true);
       notify();
    }

    @Override
    public Protocol protocol() {
        return Protocol.HTTP;
    }
    
}
