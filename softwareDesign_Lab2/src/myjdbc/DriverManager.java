package myjdbc;

public interface DriverManager {
	Connection getConnection(String u, String user, String password);
}