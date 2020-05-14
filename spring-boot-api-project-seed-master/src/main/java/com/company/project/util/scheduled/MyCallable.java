package com.company.project.util.scheduled;

import java.util.concurrent.Callable;

/**
 * https://blog.csdn.net/weixin_42606135/article/details/81282736
 * @author luxiang 
 * @date 2020年3月17日 上午9:53:33
 */
public class MyCallable<String> implements Callable<String> {

    private int tickt;
    
    public MyCallable(int tickt){
    	this.tickt = tickt;
    }
	
	@Override
	public String call() throws Exception {
		String result;
		while(tickt > 0) {
			System.out.println("票还剩余："+tickt);
			tickt--;
		}
		result = (String) "票已卖光";
		return result;
	}

}
