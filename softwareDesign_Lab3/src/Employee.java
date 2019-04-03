public class Employee implements Employment {
	private int normalTime;
	private int workingTime;
	private String name;
	private String section;
	private int theSalary;

	public Employee(int workingTime, String name, String section, int theSalary) {
		super();
		this.normalTime = 40;
		this.workingTime = workingTime;
		this.name = name;
		this.section = section;
		this.theSalary = theSalary;
	}

	public int getSalary() {
		if (workingTime >= normalTime) {
			return theSalary + 100 * (workingTime - normalTime);
		} else {
			int t = theSalary - 80 * (normalTime - workingTime);
			return t > 0 ? t : 0;
		}

	}

	@Override
	public void accept(Visitor vis) {
		// TODO Auto-generated method stub
		vis.visit(this);
	}

	public int getNormalTime() {
		return normalTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNormalTime(int normalTime) {
		this.normalTime = normalTime;
	}

	public int getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(int workingTime) {
		this.workingTime = workingTime;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

}
