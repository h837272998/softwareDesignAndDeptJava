
public class Test {
	public static void main(String[] args) {
		PRHandler d = createCoR();
		PRRequest req = new PRRequest(300000, "suv");
		d.authorize(req);
	}

	private static PRHandler createCoR() {
		// TODO Auto-generated method stub
		DivistionManager d = new  DivistionManager();
		RegionDirector r = new RegionDirector();
		ViceCOO v = new ViceCOO();
		COO c = new COO();
		d.setNextHandler(r);
		r.setNextHandler(v);
		v.setNextHandler(c);
		return d;		
	}
}
