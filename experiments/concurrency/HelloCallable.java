package com.dwivedi.experiments.concurrency;

import java.util.concurrent.Callable;

public class HelloCallable implements Callable<String> {

	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName() + ": HelloCallable.call()");
		Thread.sleep(2000);
		return (Thread.currentThread().getName() + ": HelloCallable");
	}

}
