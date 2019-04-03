public interface Employment {
	void accept(Visitor vis);

	int getSalary();
}
