package com.dea.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class VoucherReader {
    
	public ArrayList<String> readVoucher(){

        /* --- Lectura de archivo --- */
		ArrayList<String> voucherAL = new ArrayList<String>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File ("/Users/danielespinosa/eclipse-workspace/LectorFacturas/input");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null){
                voucherAL.add(linea);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        
        return voucherAL;

        /* --- Fin de lectura de archivo --- */
    }
	
}