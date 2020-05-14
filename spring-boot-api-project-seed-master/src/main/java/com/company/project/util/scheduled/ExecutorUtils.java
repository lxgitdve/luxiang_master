package com.company.project.util.scheduled;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorUtils {
	
	public static void executorServiceTest() throws Exception{
		int taskSize = 2;
		//创建一个线程池
		ExecutorService pool = Executors.newFixedThreadPool(taskSize);
		// 创建多个有返回值的任务
		List<Future> list = new ArrayList<Future>();
		for (int i = 0; i < taskSize; i++) {
			Callable c = new MyCallable(10);
			// 执行任务并获取 Future 对象
			Future f = pool.submit(c);
			list.add(f);
		}
		// 关闭线程池
		pool.shutdown();
		// 获取所有并发任务的运行结果	
		for (Future f : list) {
			// 从 Future 对象上获取任务的返回值，并输出到控制台
			System.out.println("res：" + f.get().toString());
		
	    }
	}
	
	/**
	 * 1、避免使用无界队列
		不要使用Executors.newXXXThreadPool()快捷方法创建线程池。
		因为这种方式会使用无界的任务队列，为避免OOM，我们应该使用ThreadPoolExecutor的构造方法手动指定队列的最大长度：
	 */
	public static void executorsServiceTest(){
		ExecutorService executorService = new ThreadPoolExecutor(2, 2,0, TimeUnit.SECONDS, 
												  new ArrayBlockingQueue<>(512), // 使用有界队列，避免OOM
									              new ThreadPoolExecutor.DiscardPolicy());
		
		//正确构造线程池
		int poolSize = Runtime.getRuntime().availableProcessors() * 2;
		System.out.println("poolSize = " + poolSize);
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(512);
		RejectedExecutionHandler policy = new ThreadPoolExecutor.DiscardPolicy();
		executorService = new ThreadPoolExecutor(poolSize, poolSize, 0, TimeUnit.SECONDS, queue, policy);
		executorService.execute(new MyRunnable());
		executorService.shutdown();
	}
	
	public static void solve(Executor executor, Collection<Callable<Result>> solvers) throws InterruptedException, ExecutionException {
	   CompletionService<Result> ecs = new ExecutorCompletionService<Result>(executor);// 构造器
	   for (Callable<Result> s : solvers){// 提交所有任务
	       ecs.submit(s);
	   }
	   int n = solvers.size();
	   for (int i = 0; i < n; ++i) {// 获取每一个完成的任务
	       Result r = ecs.take().get();
	       if (r != null){
	    	   System.out.println(r.toString());
	       }
	   }
	}
	
	public static void master(){
		Executor executor = Executors.newCachedThreadPool();
		Collection<Callable<Result>> solvers = new ArrayList<Callable<Result>>();
		try {
			solve(executor, solvers);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public static void testLatch(ExecutorService executorService, List<MyRunnable> tasks) throws InterruptedException{
	    CountDownLatch latch = new CountDownLatch(tasks.size());
	      for(MyRunnable r : tasks){
	          executorService.submit(new MyRunnable() {
	              @Override
	              public void run() {
	                  try{
	                      r.run();
	                  }finally {
	                      latch.countDown();// countDown
	                  }
	              }
	          });
	      }
	      System.out.println("指定超时时间:" + 2 + "SECONDS");
	      latch.await(2, TimeUnit.SECONDS); // 指定超时时间
    }
	
	public static void exeTestLatch(){
		ExecutorService executorService = Executors.newFixedThreadPool(2); 
		List<MyRunnable> tasks = new ArrayList<MyRunnable>();
		tasks.add(new MyRunnable());
		try {
			testLatch(executorService, tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
//			executorsServiceTest();
//			master();
			exeTestLatch();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
