package com.dwivedi.experiments.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStatics {
	private static int threadSafe = 0;
	private static int threadUnsafe = 0;
	private static int threadSafeCallerLock = 0;
	private static Lock lock = new ReentrantLock();

	public static int updateUnsafe() {
		int local = threadUnsafe;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadUnsafe = local + 1;
		return threadUnsafe;
	}

	public static int updateSafe() {
		lock.lock();
		try {
			int local = threadSafe;
			Thread.sleep(1);
			threadSafe = local + 1;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
		return threadSafe;
	}

	public static int updateSafeWithLock(Lock theLock) {
		theLock.lock();
		try {
			int local = threadSafeCallerLock;
			Thread.sleep(1);
			threadSafeCallerLock = local + 1;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			theLock.unlock();
		}
		
		return threadSafeCallerLock;
	}
}
