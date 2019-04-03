/**
 * 同步和同步代码块（synchronized）的区别
 * 为何使用同步：ava允许多线程并发控制，当多个线程同时操作一个可共享的资源变量时（增删改查），将会导致数据的不准确，相互之间产生冲突，因此加入同步锁以避免在该线程没有完成操作之前，被其他线程的调用，从而保证了该变量的唯一性和准确性。
 * 区别

同步方法默认用this或者当前类class对象作为锁；

同步代码块可以选择以什么来加锁，比同步方法要更细颗粒度，我们可以选择只同步会发生同步问题的部分代码而不是整个方法；
同步方法使用关键字 synchronized修饰方法，而同步代码块主要是修饰需要进行同步的代码，用synchronized（object）{代码内容}进行修饰；
 * @author HJH
 *
 */
public class NumberPrint extends Thread {
	int number;
	Object lock;

	public NumberPrint(int i, Object lock) {
		this.number = i;
		this.lock = lock;
	}

	@Override
	public void run() {
		synchronized (lock) {
			if(number == 2)
				lock.notify();
			try {
					lock.wait();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			for (int i = 0; i < 5; i++) {
				lock.notify();// 不立即释放，走完synchronize区块后才释放
				System.out.println(number);
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		// TODO Auto-generated method stub
		super.run();
	}

	public static void main(String[] args) {
		Object lock = new Object();
		NumberPrint t1 = new NumberPrint(1, lock);
		NumberPrint t2 = new NumberPrint(2, lock);
		t1.start();
		t2.start();
	}
}
