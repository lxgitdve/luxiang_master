package com.company.project.util.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

	private static int count = 0;

	static Lock lock = new ReentrantLock();

	public static void inc(){
        lock.lock();
        try {
            Thread.sleep(1000);
            count++;
            System.out.println("count=" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        	System.out.println("加锁,释放锁");
        }
	}
	
//	public static void main(String[] args) {
//		for(int i = 0; i < 10; i++){
//			MyThread t = new MyThread();
//			t.start();
//			System.out.println("tid=" + t.getId() + " tname=" + t.getName() + " count=" + count);
//		}
//	}
}
