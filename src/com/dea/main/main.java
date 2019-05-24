package com.dea.main;

import java.sql.SQLException;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) throws SQLException {

		Test t = new Test();
		
		//Header values
		String facturaId = "-1";
		String noDeCliente= "-1";
		String anyo= "-1";
		String mes= "-1";
		String dia= "-1";
		String moneda= "-1";
		float sumaDinero=0;
		
		//Item values
		String idProd= "-1";
		String prodAntique= "-1";
		String cantidad= "-1";
		String valorNeto= "-1";
		int sumaItems=0;
		
		//Trailer values
		String factura= "-1";
		String valorTotalNeto= "-1";
		
		ArrayList<String> voucher = new ArrayList<String>();
		ArrayList<String> log = new ArrayList<String>();
		
		VoucherReader vr = new VoucherReader();
		
		voucher = vr.readVoucher();
		
		for (String line : voucher) {
			switch (line.charAt(0)) {
			case 'H':
				facturaId = line.substring(1, 9).trim();
				noDeCliente = line.substring(9, 15).trim();
				anyo = line.substring(15, 19).trim();
				mes = line.substring(19, 21).trim();
				dia = line.substring(21, 23).trim();
				moneda = line.substring(23).trim();
				
				log.add(t.insertarFactura(facturaId, noDeCliente, anyo, mes, dia, moneda));
				log.add("Data attempt: " + line);
				
				sumaItems = 0;
				sumaDinero = 0;
				
				break;
			case 'I':
				sumaItems++;
				idProd = line.substring(1, 8).trim();
				prodAntique = line.substring(8, 10).trim();
				cantidad = line.substring(10, 16).trim();
				valorNeto = line.substring(16).trim();
				sumaDinero += Float.parseFloat(valorNeto);
				
				log.add(t.insertarItem(idProd, prodAntique, valorNeto));
				log.add("Data attempt: " + line);
				
				log.add(t.insertarRelacion(facturaId, idProd, cantidad));
				break;
			case 'T':
				factura = line.substring(1, 3).trim();
				valorTotalNeto = line.substring(3).trim();
				
				if(Integer.parseInt(factura) != sumaItems) {
					log.add("Item count doesn't fits: " + factura + " != " + sumaItems +"(itemSum =" + sumaItems + ")");
				}else {
					log.add("Item count OK!");
				}
				
				if(Float.parseFloat(valorTotalNeto) != sumaDinero) {
					log.add("$ count doesn't fits: " + valorTotalNeto + " != " + sumaDinero + "(moneySum = " + sumaDinero + ")");
				}else {
					log.add("Money count OK!");
				}
				
				log.add(t.insertarTrailer(factura+valorTotalNeto ,factura, valorTotalNeto));
				log.add("Data attempt: " + line);
				
				log.add(t.insertarRelacion(facturaId, factura+valorTotalNeto));
				break;
			default:
				log.add("Error! Data insertion failed");
				log.add("Data attempt: " + line);
				break;
			}
		}	
		for (String l : log) {
			t.insertarLog(l);
		}
	}
}
