import Oracle.OracleDriverManager;
import myjdbc.DriverManager;

public class GetOracleDrivermanager implements DriverManagerFactory {

	@Override
	public DriverManager getDriverManager() {
		// TODO Auto-generated method stub
		return OracleDriverManager.getOracleDriverManager();
	}

}
