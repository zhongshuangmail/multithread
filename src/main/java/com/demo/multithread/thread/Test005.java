package com.demo.multithread.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.demo.multithread.DO.User;

public class Test005 {
    public static void main(String[] args) throws InterruptedException {
        List<List<User>> list= new ArrayList<List<User>>();
        list.add(Arrays.asList(new User("һ��",11111111111L),new User("��ɵ",22222222222L)));
        list.add(Arrays.asList(new User("����",33333333333L),new User("����",44444444444L)));
        list.add(Arrays.asList(new User("����",55555555555L),new User("����ͷ",66666666666L)));
        list.add(Arrays.asList(new User("��Ϧ",77777777777L),new User("��һ",888888888888L)));
        list.add(Arrays.asList(new User("�ž�",99999999999L),new User("ʮ��",000000000000L)));
        for (List<User> users : list) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (User user : users) {
                        System.out.println("�߳�"+Thread.currentThread().getId()+"��"+user.getName()+"�ĺ���:"+user.getPhoneNum()+"���Ͷ���");
                    }
                }
            }).start();
        }
    }
}
