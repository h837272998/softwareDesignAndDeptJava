package abstractFactory;

public class Main {	
	public static void main(String[] args) {
		VehicleFactory vf = getFactory();
		
		Car car = vf.getCar();
		SUV suv = vf.getSUV();
		
		car.display();
		suv.display();
	}

	private static VehicleFactory getFactory() {
		// TODO Auto-generated method stub
		return new NonLuxuryFactory();
	}
}
