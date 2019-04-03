import java.util.HashMap;

public class FlyWeightFactory {
	private static FlyWeightFactory factory;
	private HashMap flyWeightHashMap = new HashMap();
	public FlyWeightFactory() {
		super();
	}
	
	public static FlyWeightFactory getFactory() {
		if(factory==null)
			factory = new FlyWeightFactory();
		return factory;
	}
	
	public FlyWeightInterface getFlyWeight(String div) {
		if(!flyWeightHashMap.containsKey(div))
			flyWeightHashMap.put(div, new FlyWeight("zhku", "510000", div, "gz"));
		return (FlyWeightInterface) flyWeightHashMap.get(div);
	}
	
	class FlyWeight implements FlyWeightInterface{
		final String name;
		final String zip;
		final String div;
		final String city;	
		
		@Override
		public String toString() {
			return "FlyWeight [name=" + name + ", zip=" + zip + ", div=" + div + ", city=" + city + "]";
		}

		public FlyWeight(String name, String zip, String div, String city) {
			super();
			this.name = name;
			this.zip = zip;
			this.div = div;
			this.city = city;
		}

		@Override
		public String getCompany() {
			// TODO Auto-generated method stub
			return name;
		}

		@Override
		public String getDivision() {
			// TODO Auto-generated method stub
			return div;
		}

		@Override
		public String getCity() {
			// TODO Auto-generated method stub
			return city;
		}

		@Override
		public String getZip() {
			// TODO Auto-generated method stub
			return zip;
		}
		
	}
}
