package softwareDesign_Visitor;

public class QianggeVisitor implements Visitor{
	int total;
	@Override
	public void visit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AppleTree appleTree) {
		// TODO Auto-generated method stub
		total += appleTree.countNumber();
		System.out.println(appleTree.countNumber());
	}

	@Override
	public void visit(MangoTree mangoTree) {
		// TODO Auto-generated method stub
		total += mangoTree.countNumber();
		System.out.println(mangoTree.countNumber());
	}
	
	public int getNumber() {
		return total;
	}
}
