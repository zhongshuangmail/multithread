package com.demo.multithread.thread.Future;

public class RequestParam {
    
    
    private Protocol protocol;
    
    private String paramJson;

    
    
    public RequestParam() {
        super();
    }

    public RequestParam(Protocol protocol, String paramJson) {
        super();
        this.protocol = protocol;
        this.paramJson = paramJson;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public String getParamJson() {
        return paramJson;
    }

    public void setParamJson(String paramJson) {
        this.paramJson = paramJson;
    }
    
    
    
    
}
