package Thread;
class TestThread {
	public static void main(String[] args) {
		MyThread t1 = new MyThread("t1");
		MyThread t2 = new MyThread("t2");
		t1.start();
		t2.start();
	}
}
