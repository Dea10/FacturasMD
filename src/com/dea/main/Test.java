package com.dea.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Test{
	
	public void leer() {
        String query = "";
        BD bd = new BD();
        try {
            query = "SELECT * FROM Facturas";
            Statement sentencia = bd.conectar().createStatement();
            ResultSet rs = sentencia.executeQuery(query);

            while (rs.next()) {
            	System.out.println(rs.getString("Nombre"));
            }

            sentencia.close();
            bd.conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public void insertarLog(String log) {
		String query = "INSERT INTO Logs (log) VALUES(?)";
		try {
			BD bd = new BD();
			PreparedStatement preparedStmt = bd.conectar().prepareStatement(query);
	        preparedStmt.setString(1, log);
	        preparedStmt.execute();
	        
	        bd.conexion.close();
		} catch (Exception e) {
			System.out.println("Error! Query INSERT INTO Facturas failed: " + e.getMessage());
		}
	}
	
	public String insertarFactura(
						String noDeFactura, 
						String noDeCliente, 
						String anyo, 
						String mes,
						String dia,
						String moneda
						) {
		String query = "";
        BD bd = new BD();
        try {
            query = "INSERT INTO Facturas VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStmt = bd.conectar().prepareStatement(query);
            preparedStmt.setString(1, noDeFactura);
            preparedStmt.setString(2, noDeCliente);
            preparedStmt.setString(3, anyo);
            preparedStmt.setString(4, mes);
            preparedStmt.setString(5, dia);
            preparedStmt.setString(6, moneda);
            
            preparedStmt.execute();
            
            bd.conexion.close();
        } catch (SQLException e) {
            System.out.println("Error! Query INSERT INTO Facturas failed: " + e.getMessage());
            return "Error! Query INSERT INTO Facturas failed: " + e.getMessage();
        }
        return "Data insertion OK! (Facturas)";
	}
	
	public String insertarItem(
						String idProd,
						String prodAntique,
						String valorNeto
						) {
		String query = "";
		BD bd = new BD();
		try {
			query = "INSERT INTO Items VALUES(?,?,?)";
			PreparedStatement preparedStmt = bd.conectar().prepareStatement(query);
			preparedStmt.setString(1, idProd);
			preparedStmt.setString(2, prodAntique);
			preparedStmt.setString(3, valorNeto);
			
			preparedStmt.execute();
			
			bd.conexion.close();
		} catch (Exception e) {
			System.out.println("Error! Query INSERT INTO Items failed: " + e.getMessage());
			return "Error! Query INSERT INTO Items failed: " + e.getMessage();
		}
		return "Data insertion OK! (Items)";
	}
	
	public String insertarTrailer(
						String id_trailer,
						String factura,
						String valorTotalNeto
			) {
		String query = "";
		BD bd = new BD();
		try {
			query = "INSERT INTO Trailers (id_trailer, items_total, valor_total) VALUES(?,?,?)";
			PreparedStatement preparedStmt = bd.conectar().prepareStatement(query);
			preparedStmt.setString(1, id_trailer);
			preparedStmt.setString(2, factura);
			preparedStmt.setString(3, valorTotalNeto);
			
			preparedStmt.execute();
			
			bd.conexion.close();
		} catch (Exception e) {
			System.out.println("Error! Query INSERT INTO Trailers failed: " + e.getMessage());
			return "Error! Query INSERT INTO Trailers failed: " + e.getMessage();
		}
		return "Data insertion OK! (Trailers)";
	}
	
	public String insertarRelacion(
								String idFactura,
								String idItem,
								String cantidad
			) {
		String query = "";
		BD bd = new BD();
		try {
			query = "INSERT INTO Factura_Item (id_factura, id_item, cantidad) VALUES(?,?,?)";
			PreparedStatement preparedStmt = bd.conectar().prepareStatement(query);
			preparedStmt.setString(1, idFactura);
			preparedStmt.setString(2, idItem);
			preparedStmt.setString(3, cantidad);
			
			preparedStmt.execute();
			
			bd.conexion.close();
		} catch (Exception e) {
			System.out.println("Error! Query INSERT INTO Factura_Item failed: " + e.getMessage());
			return "Error! Query INSERT INTO Factura_Item failed: " + e.getMessage();
		}
		return "Relation insertion success! -> Factura_Trailer";
	}
	
	public String insertarRelacion(
								String idFactura,
								String idTrailer
			) {
		String query = "";
		BD bd = new BD();
		try {
			query = "INSERT INTO Factura_Trailer (id_factura, id_trailer) VALUES(?,?)";
			PreparedStatement preparedStmt = bd.conectar().prepareStatement(query);
			preparedStmt.setString(1, idFactura);
			preparedStmt.setString(2, idTrailer);
			
			preparedStmt.execute();
			
			bd.conexion.close();
		} catch (Exception e) {
			System.out.println("Error! Query INSERT INTO Factura_Trailer failed: " + e.getMessage());
			return "Error! Query INSERT INTO Factura_Trailer failed: " + e.getMessage();
		}
		return "Relation insertion success! -> Factura_Trailer";
	}
}
