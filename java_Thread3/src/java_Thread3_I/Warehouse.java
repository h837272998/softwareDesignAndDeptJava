package java_Thread3_I;

public class Warehouse {
	public static final int max_size = 100; // 最大库存量
	public int curnum; // 当前库存量

	Warehouse() {
	}

	Warehouse(int curnum) {
		this.curnum = curnum;
	}
}
