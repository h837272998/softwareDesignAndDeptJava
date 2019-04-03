import java.util.Random;

public class Test {
	static String [] divs = {"����","����"};
	static String [] tt ={"��ϯ","���","�೤","ίԱ","no one"};
	static String [] fn ={"��","ǿ","��","��"};
	static String [] ln ={"��","��","��","��"};
	public static void main(String[] args) {
		FlyWeightFactory fact = FlyWeightFactory.getFactory();
		
		for (int i = 0; i < 50 ; i++) {
			VCard vc = new VCard(getRandName(), getRandTitle()
					, getRandFlyWeight());
			System.out.println(vc);
		}
	}

	private static FlyWeightInterface getRandFlyWeight() {
		// TODO Auto-generated method stub
		String div = divs[getRandIndex(divs.length)];
		return FlyWeightFactory.getFactory().getFlyWeight(div);
	}

	private static String getRandTitle() {
		// TODO Auto-generated method stub
		return tt[getRandIndex(tt.length)];
	}

	private static String getRandName() {
		// TODO Auto-generated method stub
		return ln[getRandIndex(ln.length)]+fn[getRandIndex(fn.length)];
	}
	
	public static int getRandIndex(int total) {
		Random r  = new Random();
		return Math.abs(r.nextInt())%total;
	}
}
