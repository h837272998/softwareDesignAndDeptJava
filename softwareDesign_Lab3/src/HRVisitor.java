import java.util.Vector;

public class HRVisitor implements Visitor {
	private Vector<Employee> VAEmployee = new Vector();
	private Vector<Trainee> VATrainee = new Vector();

	@Override
	public void visit(Employment e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Employee e) {
		// TODO Auto-generated method stub
		VAEmployee.add(e);
	}

	@Override
	public void visit(Trainee e) {
		// TODO Auto-generated method stub
		VATrainee.add(e);
	}

	public void display() {
		System.out.println("人力资源部统计：");
		System.out.println("===================");
		System.out.println("正式员工");
		for (int i = 0; i < VAEmployee.size(); i++) {
			Employee t = VAEmployee.elementAt(i);
			int time = t.getWorkingTime() - t.getNormalTime();
			System.out.println(t.getSection() + "的" + t.getName() + " 工作时长：" + t.getWorkingTime() + " 加班时间："
					+ (time > 0 ? time : 0) + " 请假时间：" + (time < 0 ? Math.abs(time) : 0));
		}
		int[] mm = getmaxAndMin();
		System.out.println("最勤奋员工：" + VAEmployee.elementAt(mm[0]).getName() + "("
				+ VAEmployee.elementAt(mm[0]).getWorkingTime() + "小时)    最懒惰员工：" + VAEmployee.elementAt(mm[1]).getName()
				+ "(" + VAEmployee.elementAt(mm[1]).getWorkingTime() + "小时）");
		System.out.println("-------------------");
		System.out.println("实习生");
		for (int i = 0; i < VATrainee.size(); i++) {
			Trainee t = VATrainee.elementAt(i);
			System.out.println(t.getSection() + "的" + t.getName() + " 工作时长：" + t.getWorkingTime());
		}
		System.out.println("===================");
	}

	public int[] getmaxAndMin() {
		int max = VAEmployee.elementAt(0).getWorkingTime();
		int min = VAEmployee.elementAt(0).getWorkingTime();
		int maxi = 0;
		int mini = 0;
		for (int i = 1; i < VAEmployee.size(); i++) {
			if (max < VAEmployee.elementAt(i).getWorkingTime()) {
				max = VAEmployee.elementAt(i).getWorkingTime();
				maxi = i;
			}

			if (min > VAEmployee.elementAt(i).getWorkingTime()) {
				min = VAEmployee.elementAt(i).getWorkingTime();
				mini = i;
			}
		}
		return new int[] { maxi, mini };
	}
}
