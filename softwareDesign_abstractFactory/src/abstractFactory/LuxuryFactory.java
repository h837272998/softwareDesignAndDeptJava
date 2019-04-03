package abstractFactory;

public class LuxuryFactory implements VehicleFactory{

	@Override
	public Car getCar() {
		// TODO Auto-generated method stub
		return new LuxuryCar();
	}

	@Override
	public SUV getSUV() {
		// TODO Auto-generated method stub
		return new LuxurySUV();
	}
	
}
