package softwareDesign_Visitor;

public interface Tree {
	void accept(Visitor vis);
	int countNumber();
}
