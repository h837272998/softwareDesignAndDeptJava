import java.util.Vector;

public class FDVisitor implements Visitor {
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
		System.out.println("财务部统计：");
		System.out.println("===================");
		System.out.println("正式员工");
		for (int i = 0; i < VAEmployee.size(); i++) {
			Employee t = VAEmployee.elementAt(i);
			System.out.println(t.getSection() + "的" + t.getName() + " 工作时长："
					+ t.getWorkingTime() + " 周薪：" + t.getSalary());
		}
		System.out.println("-------------------");
		System.out.println("实习生");
		for (int i = 0; i < VATrainee.size(); i++) {
			Trainee t = VATrainee.elementAt(i);
			System.out.println(t.getSection() + "的" + t.getName() + " 工作时长："
					+ t.getWorkingTime() + " 周薪：" + t.getSalary());
		}
		System.out.println("===================");
	}
}
