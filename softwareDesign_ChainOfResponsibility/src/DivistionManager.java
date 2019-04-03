
public class DivistionManager extends PRHandler {
	private final int LIMIT = 25000;
	void authorize(PRRequest req) {
		if(req.getMoney() > LIMIT){
			nextHandler.authorize(req);
			return;
		}
		System.out.println(req + " authorized by " + "DivistionManager");
	}
	
}
