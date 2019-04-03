import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainApp {
	static String[] fn = { "黄", "李", "刘", "张", "卓" };
	static String[] ln = { "生", "富", "三", "贵", "伟", "永", "静" };
	static String[] sec = { "策划部", "人力资源部", "财政部", "公关部", "司法部", "采购部" };
	static int[] salary = { 3500, 3000, 4000, 5000, 5500, 4500 };
	static int[] salary1 = { 20, 15, 25, 30, 35, 30 };

	public static void main(String[] args) {
		List<Employment> list = new ArrayList();
		XMLDemo xx = XMLDemo.getXMLDemo();
		Visitor visitor = null;
		try {
			visitor = (Visitor) Class.forName(xx.get()).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list = getEmployee(list);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).accept(visitor);

		}
		visitor.display();
	}

	public static int getRandIndex(int total) {
		Random r = new Random();
		return Math.abs(r.nextInt()) % total;
	}

	public static List<Employment> getEmployee(List list) {

		for (int i = 0; i < 20; i++) {
			int t = getRandIndex(sec.length);
			Employee e = new Employee(getRandIndex(60), fn[getRandIndex(fn.length)] + ln[getRandIndex(ln.length)],
					sec[t], salary[t]);
			list.add(e);
		}
		for (int i = 0; i < 20; i++) {
			int t = getRandIndex(sec.length);
			Trainee e1 = new Trainee(getRandIndex(40), sec[t], salary1[t],
					fn[getRandIndex(fn.length)] + ln[getRandIndex(ln.length)]);
			list.add(e1);
		}
		return list;
	}
}
