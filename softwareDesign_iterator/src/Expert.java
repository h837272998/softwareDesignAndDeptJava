import java.util.ArrayList;
import java.util.Vector;

public class Expert {
	String name;
	String age;
	String skill;
	
	public Expert(String name, String age, String skill) {
		super();
		this.name = name;
		this.age = age;
		this.skill = skill;
	}
	
	static String[][] data ={
			{"name1","30","java"},
			{"name2","22","c"},
			{"name3","34","Php"},
			{"name4","54","js"},
			{"name4","65","python"},
			{"name5","26","julia"},
		};
	
	static Vector getExperts() {
		Vector vs = new Vector<>();
		for(String []d : data) {
			vs.add(new Expert(d[0],d[1],d[2]));
		}
		return vs;
	}
	
	static ArrayList getExperts2() {
		ArrayList al = new ArrayList<>();
		for(String []d : data) {
			al.add(new Expert(d[0],d[1],d[2]));
		}
		return al;
	}

	@Override
	public String toString() {
		return "Expert [name=" + name + ", age=" + age + ", skill=" + skill + "]";
	}
	
	
}
