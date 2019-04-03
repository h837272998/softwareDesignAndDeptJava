import myjdbc.DriverManager;
import mysql.MYSQLDriverManager;

public class GetMYSQLDriverManager implements DriverManagerFactory {

	@Override
	public DriverManager getDriverManager() {
		// TODO Auto-generated method stub
		return MYSQLDriverManager.getMYSQLDriverManager();
	}

}
