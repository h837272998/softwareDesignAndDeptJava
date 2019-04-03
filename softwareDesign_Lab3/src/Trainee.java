public class Trainee implements Employment {
	private int workingTime;
	private String section;
	private int theSalaryHour;
	private String name;

	public Trainee(int workingTime, String section, int theSalary, String name) {
		super();
		this.name = name;
		this.workingTime = workingTime;
		this.section = section;
		this.theSalaryHour = theSalary;
	}

	public int getSalary() {
		return theSalaryHour * workingTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void accept(Visitor vis) {
		// TODO Auto-generated method stub
		vis.visit(this);
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
