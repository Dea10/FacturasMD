package com.dea.main;

import java.sql.Connection;
import java.sql.DriverManager;
import static com.dea.main.DataBase.*;


public interface DBConnection {

	default Connection connectToDb() {
		Connection connection = null;
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL+DB, USER, PASSWORD);
			if(connection != null)
				System.out.println("Connection OK!");
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			return connection;
		}
		
	}
	
	
}
