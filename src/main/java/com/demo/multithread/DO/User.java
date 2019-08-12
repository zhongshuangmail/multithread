package com.demo.multithread.DO;

public class User {
    private String name;
    
    private Long phoneNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public User(String name, Long phoneNum) {
        super();
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public User() {
        super();
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", phoneNum=" + phoneNum + "]";
    }
    
    
    
    
}
