package myjdbc;
public interface Statement {
	ResultSet executeQuery(String sql);
	int executeUpdata(String sql);
	Object execute(String sql);
	void close();
}