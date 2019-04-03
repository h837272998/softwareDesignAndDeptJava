package softwareDesign_Visitor;

public class MangoTree implements Tree{

	@Override
	public void accept(Visitor vis) {
		// TODO Auto-generated method stub
		vis.visit(this);
	}

	@Override
	public int countNumber() {
		// TODO Auto-generated method stub
		return 30;
	}

}
