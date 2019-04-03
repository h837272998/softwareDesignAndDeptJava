import java.util.Iterator;

public class Client {
	public static void main(String[] args) {
		Iterator it = getIterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

	private static Iterator getIterator() {
		// TODO Auto-generated method stub
		//return new AllCandidates();
		//return new CopyOfAllCandidates();
		return new Copy_2_of_AllCandidates().getIterator();
	}
}