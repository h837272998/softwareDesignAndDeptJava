public interface Visitor {
	void visit(Employment e);

	void visit(Employee e);

	void visit(Trainee e);

	void display();
}
