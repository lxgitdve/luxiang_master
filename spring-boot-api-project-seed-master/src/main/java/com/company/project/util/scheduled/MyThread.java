package com.company.project.util.scheduled;

import com.company.project.util.lock.ReentrantLockDemo;

public class MyThread extends Thread {
	
	public void run() {
//		System.out.println("MyThread.run()");
		ReentrantLockDemo.inc();
	}
	
	public static void main(String[] args) {
		try {
			for(int i = 0; i < 10; i++){
				MyThread t = new MyThread();
				t.start();
				t.sleep(3000);
				System.out.println("tid=" + t.getId() + " tname=" + t.getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
