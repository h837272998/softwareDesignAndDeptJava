package Runnable;

public class MyThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while(true) {
			System.out.println(Thread.currentThread().getName()+ " " + (++i) + " " + Math.random()) ;
			if(i==500)
				break;
		}
	}

}
