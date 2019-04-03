package mysql;

import myjdbc.Connection;
import myjdbc.DriverManager;

public class MYSQLDriverManager implements DriverManager {
	private static MYSQLDriverManager mydm;
	// ConnPool cp = new ConnPool();

	private MYSQLDriverManager() {
	}

	// 保证只能创建一个DriverManager
	public static MYSQLDriverManager getMYSQLDriverManager() {
		if (mydm == null) {
			mydm = new MYSQLDriverManager();
		}
		return mydm;
	}

	public Connection getConnection(String url, String username, String password) {
		// TODO Auto-generated method stub
		return new MYSQLConnection(url, username, password);
	}

}