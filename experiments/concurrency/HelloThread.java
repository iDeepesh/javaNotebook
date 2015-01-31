package com.dwivedi.experiments.concurrency;

public class HelloThread extends Thread {

	public HelloThread(String string) {
		super(string);
	}

	public void run() {
		for (int i = 0; i < 2; i++){
			System.out.println(getName() + " MyThread.run()");
			try {
				sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
