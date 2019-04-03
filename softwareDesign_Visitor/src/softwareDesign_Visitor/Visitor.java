package softwareDesign_Visitor;

public interface Visitor {
	void visit();
	void visit(AppleTree appleTree);
	void visit(MangoTree mangoTree);
}
