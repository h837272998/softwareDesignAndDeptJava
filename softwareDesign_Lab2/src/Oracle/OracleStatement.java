package Oracle;

import myjdbc.ResultSet;
import myjdbc.Statement;

public class OracleStatement implements Statement{

	@Override
	public ResultSet executeQuery(String sql) {
		// TODO Auto-generated method stub
		System.out.println("执行executeQuery。sql = "+sql + "; 并返回结果集");
		return new OracleResultSet();
	}

	@Override
	public int executeUpdata(String sql) {
		// TODO Auto-generated method stub
		System.out.println("执行executeUpdata。sql = "+sql + "; 并返回影响数");
		return 1;
	}

	@Override
	public Object execute(String sql) {
		// TODO Auto-generated method stub
		System.out.println("执行execute。sql = "+sql + "; 并返回多个结果集、多个更新计数");
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println("Statement close!");
	}

}