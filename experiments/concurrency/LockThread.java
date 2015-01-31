package com.dwivedi.experiments.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockThread implements Runnable {

	public void run() {
		System.out.println(Thread.currentThread().getName()
				+ " LockThread.run()");
		int threadSafe = 0;
		int threadUnsafe = 0;
		int threadSafeMyLock = 0;
		Lock lock = new ReentrantLock();
		for (int i = 0; i < 50; i++) {
			threadSafe = LockStatics.updateSafe();
			threadUnsafe = LockStatics.updateUnsafe();
			threadSafeMyLock = LockStatics.updateSafeWithLock(lock);
		}

		System.out.println(Thread.currentThread().getName()
				+ ": threadSafe value: " + threadSafe);
		System.out.println(Thread.currentThread().getName()
				+ ": threadUnsafe value: " + threadUnsafe);
		System.out.println(Thread.currentThread().getName()
				+ ": threadSafeMyLock value: " + threadSafeMyLock);
	}

}
