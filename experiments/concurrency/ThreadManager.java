package com.dwivedi.experiments.concurrency;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(Thread.currentThread().getName()
				+ " ThreadManager.main()");
		// testHelloThread();
		// testHelloRunnable();
		// testHelloCallable();
		//testExecutorService();
		// testSynchronizedMethods();
		// testSynchronizedBlocks();
		// testLocks();
		testSemaphores();
		// testConditionMonitors();
		// testObjectMonitors();
	}

	private static void testSemaphores() {
		HelloSemaphores mutex = new HelloSemaphores(1);
		runSemaphores(mutex);
		
		HelloSemaphores sem = new HelloSemaphores(10);
		runSemaphores(sem);
	}

	private static void runSemaphores(HelloSemaphores hs) {
		ExecutorService eSvc = Executors.newCachedThreadPool();
		FutureTask[] futures = new FutureTask[10];
		for(int i=0; i<futures.length; i++){
			futures[i] =  new FutureTask(hs);
			eSvc.submit(futures[i]);
		}
		
		for(int i=0; i<futures.length; i++){
			try {
				System.out.println(futures[i].get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	private static void testExecutorService() {
		try {
			Callable<String> call = new HelloCallable();
			FutureTask<String> future = new FutureTask<String>(call);
			ExecutorService eSvc = Executors.newCachedThreadPool();
			eSvc.submit(future);
			System.out.println("Started MyCallable execution....");
			System.out.println("Checking future now .... " + new Date());
			System.out.println(future.get());
			System.out.println("Got output at: " + new Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static void testObjectMonitors() {
		System.out.println("ThreadManager.testObjectMonitors()");
		ObjectMonitors om = new ObjectMonitors();
		Thread[] tArr = new Thread[10];
		for (int i = 0; i < tArr.length; i++) {
			tArr[i] = new Thread(om, ("Thread#" + i));
			tArr[i].start();

			if (i == 5) {
				// This should make all threads run in random order.
				om.setStartFlag();
				om.notifyIt();
			}
		}
	}

	private static void testConditionMonitors() {
		System.out.println("ThreadManager.testConditionMonitors()");
		Thread[] tArr = new Thread[10];
		ConditionMonitors ct = new ConditionMonitors();
		for (int i = 0; i < tArr.length; i++) {
			tArr[i] = new Thread(ct, ("Thread#" + i));
			System.out.println("Thread#" + i
					+ "Requesting counter update.. should wait for now...");
			tArr[i].start();

			if (i == 5) {
				// This should make all threads run in random order.
				ct.updateWait(false);
				ct.signal();
			}
		}
	}

	private static void testLocks() {
		LockThread lt = new LockThread();
		Thread t1 = new Thread(lt, "foo");
		Thread t2 = new Thread(lt, "bar");
		Thread t3 = new Thread(lt, "baz");
		t1.start();
		t2.start();
		t3.start();
	}

	private static void testSynchronizedBlocks() {
		SynchronizedBlock sb = new SynchronizedBlock();
		Thread t1 = new Thread(sb, "foo");
		Thread t2 = new Thread(sb, "bar");
		Thread t3 = new Thread(sb, "baz");
		t1.start();
		t2.start();
		t3.start();
	}

	private static void testSynchronizedMethods() {
		SynchronizedMethods sm = new SynchronizedMethods();
		Thread t1 = new Thread(sm, "foo");
		Thread t2 = new Thread(sm, "bar");
		Thread t3 = new Thread(sm, "baz");
		t1.start();
		t2.start();
		t3.start();
	}

	private static void testHelloCallable() {
		try {
			Callable<String> mc = new HelloCallable();
			FutureTask<String> future = new FutureTask<String>(mc);
			Thread tc = new Thread(future, "baz");
			tc.start();
			System.out.println("Started MyCallable execution....");
			System.out.println("Checking future now .... " + new Date());
			System.out.println(future.get());
			System.out.println("Got output at: " + new Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static void testHelloRunnable() {
		HelloRunnable mr = new HelloRunnable();
		Thread tr = new Thread(mr, "bar");
		tr.start();
		System.out.println("Started MyRunnable execution....");
	}

	private static void testHelloThread() {
		HelloThread mt = new HelloThread("foo");
		mt.start();
		System.out.println("Started MyThread execution....");
	}

}
