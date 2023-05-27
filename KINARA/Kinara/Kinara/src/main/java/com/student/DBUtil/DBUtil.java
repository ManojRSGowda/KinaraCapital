package com.student.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException {
		if(connection !=null) {
			return connection;
		}else {
			
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/kinara?useSSL=false";
			String user = "root";
			String password = "M@noj9898";
			
			try {
				Class.forName(driver);
				connection = DriverManager.getConnection(url,user,password);
			} catch (ClassNotFoundException e) {
			
				e.printStackTrace();
			}
		}
		
		return connection;
	}

}
