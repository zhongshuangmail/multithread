package com.demo.multithread.thread;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketLockV2 {
    /**
     * �����
     */
    private AtomicInteger serviceNum = new AtomicInteger(1);
    /**
     * �ŶӺ�
     */
    private AtomicInteger ticketNum = new AtomicInteger();
    /**
     * ����һ��ThreadLocal�����ڴ洢ÿ���̵߳��ŶӺ�
     */
    private ThreadLocal<Integer> ticketNumHolder = new ThreadLocal<Integer>();
    public void lock() {
        int currentTicketNum = ticketNum.incrementAndGet();
        // ��ȡ����ʱ�򣬽���ǰ�̵߳��ŶӺű�������
        ticketNumHolder.set(currentTicketNum);
        while (currentTicketNum != serviceNum.get()) {
            // Do nothing
        }
    }
    public void unlock() {
        // �ͷ�������ThreadLocal�л�ȡ��ǰ�̵߳��ŶӺ�
        Integer currentTickNum = ticketNumHolder.get();
        serviceNum.compareAndSet(currentTickNum, currentTickNum + 1);
    }
    
    public static void main(String[] args) {
        TicketLockV2 v2=new TicketLockV2();
        v2.lock();
        v2.unlock();
    }
}