package t2;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Customer extends Thread {
	private final List foodPanel;
	private final Condition full, empty;
	final Lock lock;

	public Customer(List foodPanel, Condition full, Condition empty, Lock lock) {
		super();
		this.foodPanel = foodPanel;
		this.full = full;
		this.empty = empty;
		this.lock = lock;
	}

	public void run() {
		while (true) {
			lock.lock();
			if (foodPanel.size() == 0) {
				try {
					empty.await();
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				System.out.println(this.getName() + " number if panel is " + foodPanel.size() + "  remove...");
				foodPanel.remove(0);
				full.signalAll();
				this.yield();
			}

			lock.unlock();
		}
	}
}
