import java.io.IOException;
import java.util.Properties;

import myjdbc.Connection;
import myjdbc.DriverManager;
import myjdbc.ResultSet;
import myjdbc.Statement;

public class Cline {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			DriverManager dm = getDriverManager();
			Class.forName("Oracle.Driver");
			conn = dm.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("加载驱动失败！");
			e.printStackTrace();
		}
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * form a");
		while (rs.next()) {
			System.out.println("姓名：" + rs.getString(0) + " 性别：" + rs.getString(1));
		}
		System.out.println();
		if (rs != null) { // 关闭记录集
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) { // 关闭声明
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) { // 关闭连接对象
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private static DriverManager getDriverManager() {
		// TODO Auto-generated method stub
		Properties p = new Properties();
		String type = "";
		try {
			p.load(ClassLoader.getSystemResourceAsStream("SQL.properties"));
			// p.setProperty("Type", key);
			type = p.getProperty("type");
			if (type.equals("mysql")) {
				DriverManagerFactory gmdm = new GetMYSQLDriverManager();
				return gmdm.getDriverManager();
			} else if (type.equals("oracle")) {
				DriverManagerFactory godm = new GetOracleDrivermanager();
				return godm.getDriverManager();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		return null;

	}
}
