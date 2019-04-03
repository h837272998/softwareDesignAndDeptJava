package java_Thread3_I;

public class Producer extends Thread {
	private int neednum; // 生产数量
	private Warehouse warehouse; // 仓库

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
						"要生产的产品数量：" + neednum + "超过剩余库存量" + (warehouse.max_size - warehouse.curnum) + "，暂时不能执行生产任务!");
				try {
					warehouse.wait();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			warehouse.curnum += neednum;
			System.out.println("已经生产了" + neednum + "个产品，现仓储量为" + warehouse.curnum);
			warehouse.notifyAll();
		}
	}
}
