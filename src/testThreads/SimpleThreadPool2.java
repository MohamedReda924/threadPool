package testThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerThread2 implements Runnable {
	private printClass p;  

	public WorkerThread2(printClass p) {
		this.p = p;
	}

	public void run() {
		p.printMessage();
	}

	

}

class printClass {
	int i = 0;
	void printMessage() {
		synchronized (this) {// synchronized block
			System.out.println(Thread.currentThread().getName() + " (Start) message = " + i++);
			processmessage();
			System.out.println(Thread.currentThread().getName() + " (End)");
			
		}
	}// end of the method
	void processmessage() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class SimpleThreadPool2 {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		printClass p = new printClass();
		
		for (int i = 0; i < 10; i++) {
			Runnable worker = new WorkerThread2(p);
			executor.execute(worker);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Finished all threads");
	}

}