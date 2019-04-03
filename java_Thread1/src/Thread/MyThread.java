package Thread;
public class MyThread extends Thread {
	MyThread(String n) {
		super(n);
	}

	public void run() {
		String tname = super.getName();
		try {
			for (int i = 1; i <= 500; i++) {
				System.out.println(tname + " " + i + " " + Math.random());
			}
		} catch (Exception e) {
			System.out.println("error");
		}
	}
}