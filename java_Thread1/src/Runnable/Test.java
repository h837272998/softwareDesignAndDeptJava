package Runnable;

public class Test {
	public static void main(String[] args) {
		MyThread mt = new MyThread();
		Thread t1 = new Thread(mt);
		Thread t2 = new Thread(mt);
		
		t1.setName("T1");
		t2.setName("T2");
		t1.start();
		t2.start();
		
	}
}
