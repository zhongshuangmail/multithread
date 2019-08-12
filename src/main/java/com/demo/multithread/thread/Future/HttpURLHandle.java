package com.demo.multithread
.thread.Future;

import org.springframework.stereotype.Component;
@Component
public class HttpURLHandle extends RequestUrlHandle {
    
    

    @Override
    public synchronized String invoke(RequestParam requestParam) throws InterruptedException{
        while(!this.isFlag()) {
            wait();
        }
        return this.getResult();
    }

    @Override
    public synchronized void receive()  throws InterruptedException {
        Thread.sleep(5000);
       if(this.isFlag()) {
          return; 
       }
       this.setFlag(true);
       this.setResult("·µ»Ø½á¹û");
       notify();
    }

    @Override
    public Protocol protocol() {
        return Protocol.HTTP;
    }
    
}
