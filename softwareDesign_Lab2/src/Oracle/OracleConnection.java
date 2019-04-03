package Oracle;

import myjdbc.Connection;
import myjdbc.Statement;

public class OracleConnection implements Connection{
	private String URL;
	private String username;
	private String password;
	
	public OracleConnection(String uRL, String username, String password) {
		super();
		URL = uRL;
		this.username = username;
		this.password = password;
		checkConnection(username,password);
	}
	
	public void checkConnection(String username2, String password2){
		if(username2!=password2){
			throw new RuntimeException("���ݿ�����ʧ�ܣ�");
		}
	}

	@Override
	public Statement createStatement() {
		// TODO Auto-generated method stub
		return new OracleStatement();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println("connection close!");
	}
}
