package com.demo.multithread.thread.Future;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;
@Component
public class Main {
    
    Map<Protocol,RequestUrlHandle> map=new ConcurrentHashMap<Protocol, RequestUrlHandle>();
    
    
    public Main(ObjectProvider<RequestUrlHandle[]> objectProvider){
        RequestUrlHandle[] loginFactories = objectProvider.getIfAvailable();
            if (!CollectionUtils.isEmpty(Arrays.asList(loginFactories))) {
                map.putAll(Arrays.stream(loginFactories).collect(Collectors.toMap(k ->k.protocol(), v -> v)));
            }
    }
    
    public void test() {
        RequestParam param=new RequestParam(Protocol.HTTP,"param");
        RequestUrlHandle requestUrlHandle = map.get(param.getProtocol());
        try {
            String request = requestUrlHandle.request(param);
            System.out.println(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

