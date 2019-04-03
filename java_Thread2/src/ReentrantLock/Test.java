package ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		Condition reach_3 = lock.newCondition();
		Condition reach_6 = lock.newCondition();

		Athread a = new Athread(lock, reach_3, reach_6);
		Bthread b = new Bthread(lock, reach_3, reach_6);
		a.start();
		b.start();
	}
}
