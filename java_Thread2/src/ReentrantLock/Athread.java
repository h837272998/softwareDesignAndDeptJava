package ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Athread extends Thread {
	private final ReentrantLock lock;
	private final Condition reach_6, reach_3;

	public Athread(ReentrantLock lock, Condition reach_3, Condition reach_6) {
		super();
		this.lock = lock;
		this.reach_6 = reach_6;
		this.reach_3 = reach_3;
	}

	// 1,2,3------7,8,9
	public void run() {
		lock.lock();
		System.out.println(1);
		System.out.println(2);
		System.out.println(3);
		reach_3.signal();
		lock.unlock();

		lock.lock();
		// 等待输出6的条件
		try {
			reach_6.await();
			System.out.println(7);
			System.out.println(8);
			System.out.println(9);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
