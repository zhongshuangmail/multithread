package com.demo.multithread.thread;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class CLHLock {
    /**
     * ����һ���ڵ㣬Ĭ�ϵ�lock״̬Ϊtrue
     */
    public static class CLHNode {
        private volatile boolean isLocked = true;
        public String a;
        
    }
    /**
     * β���ڵ�,ֻ��һ���ڵ㼴��
     */
    private volatile CLHNode tail;
    private static final ThreadLocal<CLHNode> LOCAL = new ThreadLocal<CLHNode>();
    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class,
            "tail");
    public void lock() {
        // �½��ڵ㲢���ڵ��뵱ǰ�̱߳�������
        CLHNode node = new CLHNode();
        LOCAL.set(node);
        // ���½��Ľڵ�����Ϊβ���ڵ㣬�����ؾɵĽڵ㣨ԭ�Ӳ�����������ɵĽڵ�ʵ���Ͼ��ǵ�ǰ�ڵ��ǰ���ڵ�
        CLHNode preNode = UPDATER.getAndSet(this, node);
        if (preNode != null) {
            // ǰ���ڵ㲻Ϊnull��ʾ�����������߳�ռ�ã�ͨ��������ѯ�ж�ǰ���ڵ������־λ�ȴ�ǰ���ڵ��ͷ���
            while (preNode.isLocked) {
            }
            //��ָ���κζ����ַ,����update���滹����־��preNode�ĵ�ַ��
            preNode = null;
            LOCAL.set(node);
        }
        // ���������ǰ���ڵ㣬��ʾ����û�б������߳�ռ�ã���ǰ�̻߳����
    }
    public void unlock() {
        // ��ȡ��ǰ�̶߳�Ӧ�Ľڵ�
        CLHNode node = LOCAL.get();
        // ���tail�ڵ����node����tail�ڵ����Ϊnull��ͬʱ��node��lock״ְ̬λfalse����ʾ��ǰ�߳��ͷ�����
        if (!UPDATER.compareAndSet(this, node, null)) {
            node.isLocked = false;
        }
        node = null;
    }
    
    
    public static void main(String[] args) {
        
        CLHLock lock =new CLHLock();
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    lock.lock();
                    lock.lock();
                    System.out.println(Thread.currentThread().getId());
                    lock.unlock();
                    lock.unlock();
                }
            }).start();
            
       
    }
    
    
    
}