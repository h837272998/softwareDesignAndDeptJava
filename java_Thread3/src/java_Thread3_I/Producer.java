package java_Thread3_I;

public class Producer extends Thread {
	private int neednum; // ��������
	private Warehouse warehouse; // �ֿ�

	public Producer(int neednum, Warehouse godown) {
		// TODO Auto-generated constructor stub
		this.neednum = neednum;
		this.warehouse = godown;
	}

	public void run() {
		produce(neednum);
	}

	public synchronized void produce(int neednum) {
		synchronized (warehouse) {
			while (neednum + warehouse.curnum > warehouse.max_size) {
				System.out.println(
						"Ҫ�����Ĳ�Ʒ������" + neednum + "����ʣ������" + (warehouse.max_size - warehouse.curnum) + "����ʱ����ִ����������!");
				try {
					warehouse.wait();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			warehouse.curnum += neednum;
			System.out.println("�Ѿ�������" + neednum + "����Ʒ���ֲִ���Ϊ" + warehouse.curnum);
			warehouse.notifyAll();
		}
	}
}
