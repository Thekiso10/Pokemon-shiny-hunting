package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Documentos {
	
	private final String ruta = "resource/save.txt";
	
	public Documentos() {
		
	}
	
	public boolean GuardarDatos(ArrayList<String> preDatos) {
		boolean correcto = true;
		
		FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);

            for (String str : preDatos) {
				pw.print(str + "\n");
			}

        } catch (Exception e) {
        	correcto = false;
        } finally {
           try {
        	   
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
        	   correcto = false;
           }
        }
		
		return correcto;
	}
	
	public boolean CargarDatos(ArrayList<String> preDatos) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		boolean correcto = true;
		
		try {
			
			archivo = new File (ruta);
	        fr = new FileReader (archivo);
	        br = new BufferedReader(fr);
	        //Lectura del fichero
	        String linea;
	        
	        while((linea=br.readLine())!=null) 
	        	preDatos.add(linea);
			
		}catch (Exception e) {
			correcto = false;
		}finally{
	         
			try{                    
	            
	        	 if(fr != null)
	        		 fr.close();     
	                              
	         }catch (Exception e2){ 
	        	 correcto = false;
	         }
		}
		
		return correcto;
	}
	
	public boolean ExistFile() {
		return (new File(ruta)).exists();
	}
}
