package com.dwivedi.experiments.concurrency;

public class SynchronizedStatics {
	
	private static int sThreadSafe = 0;
	private static int sThreadUnsafe = 0;
	
	public synchronized static void updateStaticSafeMethod() {
		int local = sThreadSafe + 1;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sThreadSafe = local;

		System.out.println(Thread.currentThread().getName()
				+ "->The sThreadSafe value is: " + sThreadSafe);
	}
	
	public static void updateStaticUnsafeMethod(){
		int local = sThreadUnsafe + 1;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sThreadUnsafe = local;

		System.out.println(Thread.currentThread().getName()
				+ "->The sThreadUnsafe value is: " + sThreadUnsafe);
	}

	public static void updateStaticSafeBlock() {
		synchronized (SynchronizedStatics.class) {
			int local = sThreadSafe + 1;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sThreadSafe = local;
		}
		System.out.println(Thread.currentThread().getName()
				+ "->The sThreadSafe value is: " + sThreadSafe);
	}

	public static void updateStaticUnsafeBlock() {
		int local = sThreadUnsafe + 1;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sThreadUnsafe = local;
		System.out.println(Thread.currentThread().getName()
				+ "->The sThreadUnsafe value is: " + sThreadUnsafe);
	}
}
