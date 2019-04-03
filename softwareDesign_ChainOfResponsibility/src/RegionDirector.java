
public class RegionDirector extends PRHandler {

	private static final int LIMIT = 100000;

	void authorize(PRRequest req) {
				if(req.getMoney() > LIMIT){
					nextHandler.authorize(req);
					return;
				}
				System.out.println(req + 
						" authorized by " + "RegionDirector");
	}

}
