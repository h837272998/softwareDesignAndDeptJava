package t_1;

class Person2 implements Cloneable {
	private Car2 car;
	private String name;
	
	public Person2(String s, String t) {
	    name = s;
	    car = new Car2(t);
	}
	public Car2 getCar() {
		return car;
	}
	public void setCar(Car2 car) {
		this.car = car;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Object clone() {
		Person2 p = new Person2(name,car.getName());
		return p;
	}
}
	class Car2 {
		private String name;

		public Car2(String s) {
			this.name = s;
		}
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
public class DeepCopyTest {

	  public static void main(String[] args) {
	    //Original Object
	    Person2 p = new Person2("Person-A","Civic");
	    System.out.println("Original (orginal values): " +
	                       p.getName() + " - " + 
	                       p.getCar().getName());

	    //Clone as a shallow copy
	    Person2 q = (Person2) p.clone();

	    System.out.println("Clone (before change): " +
	                       q.getName() + " - " + 
	                       q.getCar().getName());

	    //change the primitive member
	    q.setName("Person-B");

	    //change the lower-level object
	    q.getCar().setName("Accord");

	    System.out.println("Clone (after change): " +
	                       q.getName() + " - " + 
	                       q.getCar().getName());

	    System.out.println(
	      "Original (after clone is modified): " +
	      p.getName() + " - " + p.getCar().getName());

	  }
	}
