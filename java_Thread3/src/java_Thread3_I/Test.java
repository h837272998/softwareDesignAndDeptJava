package java_Thread3_I;

/**
 * Java�̣߳�����Э��-������������ģ��
 * 
 * 
 */
public class Test {
	public static void main(String[] args) {
		// ��ʼ���30����Ʒ
		Warehouse warehouse = new Warehouse(30);
		// 3��������
		Consumer c1 = new Consumer(50, warehouse);
		Consumer c2 = new Consumer(20, warehouse);
		Consumer c3 = new Consumer(30, warehouse);
		Consumer c4 = new Consumer(30, warehouse);
		// 7��������
		Producer p1 = new Producer(10, warehouse);
		Producer p2 = new Producer(10, warehouse);
		Producer p3 = new Producer(10, warehouse);
		Producer p4 = new Producer(10, warehouse);
		Producer p5 = new Producer(30, warehouse);
		Producer p6 = new Producer(10, warehouse);
		Producer p7 = new Producer(88, warehouse);

		c1.start();
		c2.start();
		c3.start();
		c4.start();
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();
		p6.start();
		p7.start();
	}
}