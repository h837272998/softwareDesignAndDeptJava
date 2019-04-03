package myjdbc;
public interface ResultSet {
	boolean next();
	String getString(int i);
	void close();
}