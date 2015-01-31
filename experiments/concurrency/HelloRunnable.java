package com.dwivedi.experiments.concurrency;

public class HelloRunnable implements Runnable {

	public void run() {
		for (int i = 0; i < 2; i++){
			System.out.println(Thread.currentThread().getName() + " MyRunnable.run()");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
