package jp.ncsj.datamanager;

import  java.sql.*;
import  java.util.ArrayList;

public class DBMSDataManager extends DataManager{
	String user		= "root";
	String passwd	= "root";
	String url		= "jdbc:mysql://localhost/cgk2020";
	String driver	= "com.mysql.cj.jdbc.Driver";

	Connection con	= null;
	Statement  stmt = null;

	public DBMSDataManager(){
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}

	@Override
	public void save(Employee emp){
		try{
			String sql = String.format("INSERT INTO employee VALUES('%s','%s','%s')",emp.id,emp.name,emp.div);
			int rtc = stmt.executeUpdate(sql);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}

	@Override
	public Employee [] list(){
		Employee [] emps = null;

		ArrayList <Employee> array = new ArrayList <Employee> ();
		try{
			ResultSet rs = stmt.executeQuery("select * from employee");
			while(rs.next()){
				String id = rs.getString(1);
				String name = rs.getString(2);
				String div  = rs.getString(3);

				Employee emp = new Employee(id,name,div);
				array.add(emp);
			}
		}
		catch(Exception e){
			System.out.println(e.toString());
		}

		emps = new Employee [array.size()];
		for(int i=0;i<array.size();i++){
			emps[i] = array.get(i);
		}

		return emps;
	}

	@Override
	public void close(){
		try{
			if(stmt != null){
				stmt.close();
			}

			if(con != null){
				con.close();
			}
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
