package t2;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Cooker extends Thread {
	private final List foodPanel;
	private final Condition full, empty;
	final Lock lock;

	public Cooker(List foodPanel, Condition full, Condition empty, Lock lock) {
		super();
		this.foodPanel = foodPanel;
		this.full = full;
		this.empty = empty;
		this.lock = lock;
	}

	public void run() {
		while (true) {
			lock.lock();
			if (foodPanel.size() > 3) {
				try {
					full.await();
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				foodPanel.add("food");
				System.out.println(this.getName() + " number if panel is " + foodPanel.size() + "  After add...");
				empty.signalAll();
				this.yield();
			}
			lock.unlock();
		}
	}

}
