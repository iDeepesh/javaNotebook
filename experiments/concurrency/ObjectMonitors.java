package com.dwivedi.experiments.concurrency;

public class ObjectMonitors implements Runnable {
	private static boolean start = false;

	public void run() {
		synchronized (this) {
			try {
				while (!start){
					System.out.println("Thread#" + Thread.currentThread().getName() + ": Waiting....");
					this.wait();
				}
				
				System.out.println("Thread#" + Thread.currentThread().getName() + ": Executing now..");
				Thread.sleep(10);
				
				//Notify others now.
				this.notify();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setStartFlag() {
		start = true;
	}

	public void notifyIt() {
		synchronized(this){
			this.notify();
		}
	}

}
