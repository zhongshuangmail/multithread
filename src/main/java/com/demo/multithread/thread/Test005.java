package com.demo.multithread.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.demo.multithread.DO.User;

public class Test005 {
    public static void main(String[] args) throws InterruptedException {
        List<List<User>> list= new ArrayList<List<User>>();
        list.add(Arrays.asList(new User("一休",11111111111L),new User("二傻",22222222222L)));
        list.add(Arrays.asList(new User("三张",33333333333L),new User("四李",44444444444L)));
        list.add(Arrays.asList(new User("五王",55555555555L),new User("六大头",66666666666L)));
        list.add(Arrays.asList(new User("七夕",77777777777L),new User("八一",888888888888L)));
        list.add(Arrays.asList(new User("九九",99999999999L),new User("十世",000000000000L)));
        for (List<User> users : list) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (User user : users) {
                        System.out.println("线程"+Thread.currentThread().getId()+"给"+user.getName()+"的号码:"+user.getPhoneNum()+"发送短信");
                    }
                }
            }).start();
        }
    }
}
