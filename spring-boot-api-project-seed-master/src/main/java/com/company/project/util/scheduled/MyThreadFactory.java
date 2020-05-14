package com.company.project.util.scheduled;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory {

	private static final String DEFAULT_POOL_NAME_PREFIX = "test_pool_";
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private String pool_name_prefix;
	
	public MyThreadFactory() {
		this.pool_name_prefix = DEFAULT_POOL_NAME_PREFIX;
	}
	public MyThreadFactory(String name) {
		this.pool_name_prefix = name;
	}
	
	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r, pool_name_prefix + threadNumber.getAndIncrement());
		return thread;
	}


}
