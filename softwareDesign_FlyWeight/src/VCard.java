public class VCard implements FlyWeightInterface{
	String name ;
	String title;
	final FlyWeightInterface fwi;
	public VCard(String name, String title, FlyWeightInterface fwi) {
		super();
		this.name = name;
		this.title = title;
		this.fwi = fwi;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getCompany() {
		// TODO Auto-generated method stub
		return fwi.getCompany();
	}
	@Override
	public String getDivision() {
		// TODO Auto-generated method stub
		return fwi.getDivision();
	}
	@Override
	public String getCity() {
		// TODO Auto-generated method stub
		return fwi.getCity();
	}
	@Override
	public String getZip() {
		// TODO Auto-generated method stub
		return fwi.getZip();
	}

	@Override
	public String toString() {
		return "Vcard [name=" + name + ", title=" + title + ", fwi=" + fwi + "]";
	}
	
	
}
