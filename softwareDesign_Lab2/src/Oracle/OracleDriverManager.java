package Oracle;

import myjdbc.Connection;
import myjdbc.DriverManager;

public class OracleDriverManager implements DriverManager {
	private static OracleDriverManager odm;
	// ConnPool cp = new ConnPool();

	private OracleDriverManager() {
	}

	// 保证只能创建一个DriverManager
	public static OracleDriverManager getOracleDriverManager() {
		if (odm == null) {
			odm = new OracleDriverManager();
		}
		return odm;
	}

	public Connection getConnection(String url, String username, String password) {
		// TODO Auto-generated method stub
		return new OracleConnection(url, username, password);
	}

}