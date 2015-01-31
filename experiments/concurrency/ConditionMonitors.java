package com.dwivedi.experiments.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionMonitors implements Runnable {

	final private Lock lock = new ReentrantLock();
	final private Condition condition = lock.newCondition();
	private boolean wait = true;
	private int counter = -1;

	public void run() {
		lock.lock();
		try {
			while (wait){
				System.out.println(Thread.currentThread().getName() + ": Going to wait for now....");
				condition.await();
			}
			
			System.out.println(Thread.currentThread().getName() + ": Now updating counter...");
			counter = -1;
			while (counter < 100){
				counter++;
			}
			
			//Signal other threads now.
			condition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		finally{
			lock.unlock();
		}
	}
	
	public void updateWait(boolean newWait){
		wait = newWait;
	}

	public void signal() {
		lock.lock();
		condition.signal();
		lock.unlock();
	}
}
