package t_1;
class Person implements Cloneable {
	private Car car;
	private String name;
	
	public Person(String s, String t) {
	    name = s;
	    car = new Car(t);
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Object clone() {
		try {
			return super.clone();
		}catch(CloneNotSupportedException e) {
			return null;
		}
	}
}
class Car {
	private String name;

	public Car(String s) {
		this.name = s;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
public class shallowCopyTest {
	public static void main(String[] args) {
		Person p  = new Person("Person-1","ben");
		System.out.println("The Original(Original values):" + p.getName() + " - " + p.getCar().getName());
		Person Copyp = (Person) p.clone();
		System.out.println("The Clone(before change):" + Copyp.getName() + " - " + Copyp.getCar().getName());
		Copyp.setName("Person-2");
		Copyp.getCar().setName("BMW");
		System.out.println("The Clone(after change):" + Copyp.getName() + " - " + Copyp.getCar().getName());
		System.out.println("after Clone is modified:" + p.getName() + " - " + p.getCar().getName());
	}
}
