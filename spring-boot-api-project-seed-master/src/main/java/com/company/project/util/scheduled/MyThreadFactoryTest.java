package com.company.project.util.scheduled;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadFactoryTest {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(2), new MyThreadFactory("this is pool name"),
				new RejectedExecutionHandler() {

					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						try {
							if (executor.isShutdown()) {
								return;
							}
							executor.getQueue().put(r);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});

		try {
			for (int i = 0; i < 10; i++) {
				executor.submit(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.currentThread().sleep(3000);
							System.out.println(Thread.currentThread().getName());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});
			}
		} finally {
			executor.shutdown();
		}
	}

}
