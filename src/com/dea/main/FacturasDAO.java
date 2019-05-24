package com.dea.main;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface FacturasDAO extends DBConnection{

	default void read() {
		try(Connection connection = connectToDb()){
			String query = "SELECT * FROM persona";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString("nombre") + rs.getString("ap1") + rs.getString("ap2"));
			}
			
		}catch(SQLException e){
			
		}
	}
	
}
