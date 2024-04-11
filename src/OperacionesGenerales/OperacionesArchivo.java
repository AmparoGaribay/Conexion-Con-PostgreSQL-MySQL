/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperacionesGenerales;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *Encargada del manejo y creacion de los archivos bat se creo aparte debido a que no podia ejecutarse directamente
 * ademas de que ocupaba demasiadas lineas en la ventana grafica.
 * 
 * @author AMPARO
 */
public class OperacionesArchivo {
    File archivo;//abre el archivo
    Scanner leer;//leer el archivo
    FileWriter escribir;
    String psd;
    
    public void GuardarComoAchivo(String NombreArchivo, String texto ) throws FileNotFoundException, IOException{
        archivo= new File(NombreArchivo);
        escribir= new FileWriter(archivo);
        escribir.append(texto);
        escribir.close();

        
    }
    
    
    public void batCrearBackup(String base, String ruta){ //parametros que recibe (String ruta, String base);
        try {
            archivo= new File("C:\\Respaldos\\makeBackup.bat");//.bat
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
                                    "pg_dump  -h localhost -p 5432 -U postgres -F c -b -v -f \""+ruta+"\\"+base+"%fechac%%hora%.backup\" "+ base+ " \n" +
                                    "pause"+ " \n" +
                                    "exit");
              //-------------------------------------------------------------------------
                    System.err.println("Se creo Archivo [mkbackup.bat]");  
                
                    escribir.close();
                    archivo.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(OperacionesArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    public void SetContraseña (String p){//no usado
        psd=p;
    }
    
    public void batRestoreSobrescribe(String base, String rutaArchivo){
        try {
            archivo= new File("C:\\Respaldos\\RestoreSobrescritura.bat");//.bat
            escribir= new FileWriter(archivo);
            
                escribir.append("SET PGPASSWORD=tecnica87\n" +
                "\n" +
                "dropdb -h localhost -p 5432 -U postgres " + base +"\n" +
                "createdb -h localhost -p 5432 -U postgres " + base +"\n" +
                "\n" +
                "pg_restore -h localhost -p 5432 -U postgres -d " +base+" -v \"" +rutaArchivo +"\"\n" +
                "\n" +
                "pause");
                System.err.println("Se creo Archivo [rsSobrescritura.bat]");  
                
                escribir.close();
                archivo.createNewFile();
            
        } catch (IOException ex) {
            Logger.getLogger(OperacionesArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void batRestoreNuevaB(String baseNueva, String ruta){
        
           
        try {
            archivo= new File("C:\\Respaldos\\RestoreNuevabase.bat");//.bat
             escribir= new FileWriter(archivo);
            escribir.append("SET PGPASSWORD=tecnica87\n" +
                            "\n" +
                      
                            "createdb -h localhost -p 5432 -U postgres "+baseNueva+"\n" +
                            "\n" +
                            "pg_restore -h localhost -p 5432 -U postgres -d"+baseNueva+" -v \""+ruta+"\"\n" +
                            "\n" +
                            "pause");
            
                    System.err.println("Se creo Archivo [rsNuevabat.bat]");  
                
                    escribir.close();
                    archivo.createNewFile();
            
        } catch (IOException ex) {
            Logger.getLogger(OperacionesArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
            
    public void batRestauratablasyotros(String base, String rutaArchivo){
        try {
            archivo= new File("C:\\Respaldos\\RestoreNuevabaseyTablas.bat");//.bat
            escribir= new FileWriter(archivo);
            escribir.append("SET PGPASSWORD=tecnica87\n"
                    + "\n"
                    + "pg_restore -h localhost -p 5432 -U postgres -d " +base+" -v \"" +rutaArchivo +"\"\n"
                    + "pause");
            System.out.println("Se creo Archivo [rsRestTabY0]");
            escribir.close();
            archivo.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(OperacionesArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }       
    
    public void batReturnConsole(){//ejecuta el bat y realiza una prueba del mismo.
     Runtime ejecutor= Runtime.getRuntime();
     try{
         ejecutor.exec("cmd.exe /K start C:\\Respaldos\\makeBackup.bat");//direccion hace referencia al path del archivo.bat que cree previamente.
        }  catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error al ejecutar"+"\n" +ex);
        }
     
    }
    
    public void batReturnRestoreNuevabase(){//ejecuta el bat para restore tercera ventana;
     Runtime ejecutor= Runtime.getRuntime();
     try{
         ejecutor.exec("cmd.exe /K start C:\\Respaldos\\RestoreNuevabase.bat");//direccion hace referencia al path del archivo.bat que cree previamente.
        }  catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error al ejecutar"+"\n" +ex);
        }
       
    }
    
    public void batReturnRestore2sobrescritura(){//ejecuta el bat para restore tercera ventana;
     Runtime ejecutor= Runtime.getRuntime();
     try{
         ejecutor.exec("cmd.exe /K start C:\\Respaldos\\RestoreSobrescritura.bat");//direccion hace referencia al path del archivo.bat que cree previamente.
        }  catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error al ejecutar"+"\n" +ex);
        }
       
    }
    
    public void batReturnsobrescribirGatito(){//ejecuta el bat para restore tercera ventana;
     Runtime ejecutor= Runtime.getRuntime();
     try{
         ejecutor.exec("cmd.exe /K start C:\\Respaldos\\RestoreNuevabaseyTablas.bat");//direccion hace referencia al path del archivo.bat que cree previamente.
        }  catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error al ejecutar"+"\n" +ex);
        }
       
    }
    
}//class
