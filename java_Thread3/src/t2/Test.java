package t2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		Condition full = lock.newCondition();
		Condition empty = lock.newCondition();
		final List foodPanel = new ArrayList();

		for (int i = 0; i < 3; i++)
			new Customer(foodPanel, full, empty, lock).start();

		for (int i = 0; i < 8; i++)
			new Cooker(foodPanel, full, empty, lock).start();

	}
}
