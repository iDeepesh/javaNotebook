package com.dwivedi.experiments.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class HelloSemaphores implements Callable<String> {

	private Semaphore sem = null;
	private int counter = 0;
	private int threadLimit = 0;

	public HelloSemaphores(int threadLimit) {
		this.sem = new Semaphore(threadLimit);
		this.threadLimit = threadLimit;
	}

	public String call() throws Exception {

		sem.acquire();

		for (int i = 0; i < 100; i++) {
			int local = counter + 1;
			Thread.sleep(1);
			counter = local;
		}

		String str = String.format(
				"%s: Thread Limit: %d The counter value is %d", Thread
						.currentThread().getName(), threadLimit, counter);

		sem.release();

		return str;
	}

}
