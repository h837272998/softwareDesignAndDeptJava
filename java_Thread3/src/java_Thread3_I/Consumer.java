package java_Thread3_I;

public class Consumer extends Thread {
	private int neednum;
	private Warehouse warehouse;

	public Consumer(int neednum, Warehouse warehouse) {
		super();
		this.neednum = neednum;
		this.warehouse = warehouse;
	}

	public void run() {
		consumer(neednum);
	}

	private void consumer(int n) {
		// TODO Auto-generated method stub
		synchronized (warehouse) {
			while (warehouse.curnum < n) {
				try {
					warehouse.wait();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			warehouse.curnum -= n;
			System.out.println("�Ѿ�������" + neednum + "����Ʒ���ֲִ���Ϊ" + warehouse.curnum);
			// �����ڴ˶���������ϵȴ��������߳�
			warehouse.notifyAll();
		}
	}
}
