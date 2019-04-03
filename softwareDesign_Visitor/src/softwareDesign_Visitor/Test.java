package softwareDesign_Visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		QianggeVisitor q = new QianggeVisitor();
		List<Tree> forest = getForest();
		
		for (Tree tree : forest) {
			tree.accept(q);
		}
		
		System.out.println(q.getNumber());
	}

	private static List<Tree> getForest() {
		// TODO Auto-generated method stub
		List<Tree> ts = new ArrayList();
		for (int i = 0; i < 100; i++) {
			ts.add(new AppleTree());
		}
		
		for (int i = 0; i < 200; i++) {
			ts.add(new MangoTree());
		}
		return ts;
	}
}
