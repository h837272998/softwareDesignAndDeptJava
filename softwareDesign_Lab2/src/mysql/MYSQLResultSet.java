package mysql;

import java.util.Vector;

import myjdbc.ResultSet;

public class MYSQLResultSet implements ResultSet{
	int effectNumber;
	int currentNumber = 0 ;
	Vector v;
	
	public MYSQLResultSet() {
		// TODO Auto-generated constructor stub
		this.effectNumber = 10;
		this.v = new Vector();
		for(int i = 0;i<=10;i++) {
			String[] temp = {"Ãû×Ö"+i,"ÄÐ"}; 
			v.insertElementAt(temp,i);
		}
	}
	@Override
	public boolean next() {
		// TODO Auto-generated method stub
		currentNumber++;
		return effectNumber>currentNumber;
	}
	@Override
	public String getString(int i) {
		// TODO Auto-generated method stub
		if(effectNumber>=currentNumber) {
			String[] t =  (String[]) v.elementAt(currentNumber);
			return t[i];
		}
		return null;
	}
	
	public int getEffectNumber() {
		return 10;
	}
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println("ResultSet close!");
	}
}
