package ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Bthread extends Thread {
	private final ReentrantLock lock;
	private final Condition reach_3;
	private final Condition reach_6;

	public Bthread(ReentrantLock lock, Condition reach_3, Condition reach_6) {
		this.lock = lock;
		this.reach_6 = reach_6;
		this.reach_3 = reach_3;
	}

	// 4,5,6
	public void run() {
		try {
			lock.lock();
			// 等待3输出完毕的信号
			reach_3.await();
			System.out.println(4);
			System.out.println(5);
			System.out.println(6);
			reach_6.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
