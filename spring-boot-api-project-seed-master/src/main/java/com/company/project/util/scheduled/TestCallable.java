package com.company.project.util.scheduled;

import java.util.concurrent.Callable;

public class TestCallable<V> implements Callable<V> {

	@Override
	public V call() throws Exception {
		Result r = new Result("1","张三");
		return (V) r;
	}
	
	

}
