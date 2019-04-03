
public class COO extends PRHandler {
	private final int LIMIT = 400000;
	void authorize(PRRequest req) {
		System.out.println(req + " authorized by " + "COO");
	}
	
}
