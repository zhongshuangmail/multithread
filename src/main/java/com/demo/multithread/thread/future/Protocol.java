package com.demo.multithread.thread.future;

public enum Protocol {
    
    DUBBO("DUBBO",1),HTTP("HTTP",2);
    
    private String name;
    private Integer index;
    
    Protocol(String name,Integer index){
        this.name=name;
        this.index=index;
    }
    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getIndex() {
        return index;
    }
    public void setIndex(Integer index) {
        this.index = index;
    }
    
    
    
}
