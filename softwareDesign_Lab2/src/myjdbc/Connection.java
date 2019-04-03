package myjdbc;
public interface Connection {
	Statement createStatement();

	void close();
}