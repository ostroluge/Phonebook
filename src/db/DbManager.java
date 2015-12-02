package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

	private static DbManager INSTANCE;
	
	public static DbManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DbManager();
		}
		return INSTANCE;
	}

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://172.18.15.22:3306/ostrowski?user=ostrowski&password=dbpassword");
			
			if (conn != null) {
				return conn;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
