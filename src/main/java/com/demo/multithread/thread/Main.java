package com.demo.multithread.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Main {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Integer> call=new Task();
		FutureTask future=new futureTask(call);
		new Thread(future).start();
		Thread.sleep(4000);
		
		System.out.println(future.get());
		
    }
}
class Task implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++)
            sum += i;
        return sum;
    }
}


class futureTask  extends FutureTask<Integer>{

	public futureTask(Callable<Integer> callable) {
		super(callable);
	}

	@Override
	protected void done() {
		System.out.println("执行完毕");
	}
	
}
