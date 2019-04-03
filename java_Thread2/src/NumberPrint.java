/**
 * ͬ����ͬ������飨synchronized��������
 * Ϊ��ʹ��ͬ����ava������̲߳������ƣ�������߳�ͬʱ����һ���ɹ������Դ����ʱ����ɾ�Ĳ飩�����ᵼ�����ݵĲ�׼ȷ���໥֮�������ͻ����˼���ͬ�����Ա����ڸ��߳�û����ɲ���֮ǰ���������̵߳ĵ��ã��Ӷ���֤�˸ñ�����Ψһ�Ժ�׼ȷ�ԡ�
 * ����

ͬ������Ĭ����this���ߵ�ǰ��class������Ϊ����

ͬ����������ѡ����ʲô����������ͬ������Ҫ��ϸ�����ȣ����ǿ���ѡ��ֻͬ���ᷢ��ͬ������Ĳ��ִ������������������
ͬ������ʹ�ùؼ��� synchronized���η�������ͬ���������Ҫ��������Ҫ����ͬ���Ĵ��룬��synchronized��object��{��������}�������Σ�
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
				lock.notify();// �������ͷţ�����synchronize�������ͷ�
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
