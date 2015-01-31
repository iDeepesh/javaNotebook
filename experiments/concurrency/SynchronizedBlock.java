package com.dwivedi.experiments.concurrency;

public class SynchronizedBlock implements Runnable {

	private int threadSafe = 0;
	private int threadUnsafe = 0;

	public void run() {
		for (int i = 0; i < 50; i++) {
			updateSafe();
			updateUnsafe();
			SynchronizedStatics.updateStaticSafeBlock();
			SynchronizedStatics.updateStaticUnsafeBlock();
		}
	}

	private void updateSafe() {
		synchronized (this) {
			int local = threadSafe + 1;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			threadSafe = local;
		}
		System.out.println(Thread.currentThread().getName()
				+ "->The threadSafe value is: " + threadSafe);
	}

	private void updateUnsafe() {
		int local = threadUnsafe + 1;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadUnsafe = local;
		System.out.println(Thread.currentThread().getName()
				+ "->The threadUnsafe value is: " + threadUnsafe);
	}
}
