/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperacionesGenerales;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AMPARO
 */
public class pruebasparabat {
    File archivo;//abre el archivo
    Scanner leer;//leer el archivo
    FileWriter escribir;
    
     public void batCrearBackup2(String base, String ruta){ //parametros que recibe (String ruta, String base);
        try {
            archivo= new File("C:\\Respaldos\\makeBackup2.bat");//.bat
            escribir= new FileWriter(archivo);
            
                    escribir.append("@echo off\n" +
"cls\n" +
"set anio=%date:~6,4% \n" +
"set mes=%date:~3,2%\n" +
"set dia=%date:~0,2%\n" +
"set hora=%time:~0,-3%\n" +
"set ape=%anio%%mes%%dia%\n" +
"\n" +
"set fecha=%date%\n" +
"\n" +
"set fecha=%fecha:/=%\n" +
"\n" +
"set fechac=%fecha: =%\n" +
"\n" +
"set hora=%hora::=%\n" +
"\n" +
"set hora=%hora: =%\n" +
"\n" +
"SET PGPASSWORD=tecnica87\n" +
"\n" +
"pg_dump  -h localhost -p 5432 -U postgres -F c -b -v -f \""+ruta+"\\"+base+"%fechac%%hora%.backup\""+ base+"\n" +
"pause");
                    
                    
                    
                    
                    
              //-------------------------------------------------------------------------
                      
                      
                    
                    escribir.close();
                    archivo.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(OperacionesArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
}
