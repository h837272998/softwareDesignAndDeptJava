package Oracle;

import myjdbc.ResultSet;
import myjdbc.Statement;

public class OracleStatement implements Statement{

	@Override
	public ResultSet executeQuery(String sql) {
		// TODO Auto-generated method stub
		System.out.println("ִ��executeQuery��sql = "+sql + "; �����ؽ����");
		return new OracleResultSet();
	}

	@Override
	public int executeUpdata(String sql) {
		// TODO Auto-generated method stub
		System.out.println("ִ��executeUpdata��sql = "+sql + "; ������Ӱ����");
		return 1;
	}

	@Override
	public Object execute(String sql) {
		// TODO Auto-generated method stub
		System.out.println("ִ��execute��sql = "+sql + "; �����ض���������������¼���");
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println("Statement close!");
	}

}