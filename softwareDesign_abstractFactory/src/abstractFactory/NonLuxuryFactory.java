package abstractFactory;

public class NonLuxuryFactory implements VehicleFactory {

	@Override
	public Car getCar() {
		// TODO Auto-generated method stub
		return new NonLuxuryCar();
	}

	@Override
	public SUV getSUV() {
		// TODO Auto-generated method stub
		return new NonLuxurySUV();
	}

}