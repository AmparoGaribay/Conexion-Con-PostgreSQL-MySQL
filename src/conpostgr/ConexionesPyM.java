/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conpostgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author AMPARO
 */
public class ConexionesPyM {
          public ResultSet resultado =null;
         public Connection conn =null;
         
    public void conectarP() {
    String cadenaConex="jdbc:postgresql://127.0.0.1/apli_usuario?"+"user=postgres&password=tecnica87";
    try {
        Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection(cadenaConex);
            //JOptionPane.showMessageDialog(null,"Conectado");
        }catch(Exception e){
        System.err.println("Error:"+e.getMessage()+e.getStackTrace());
        conn=null;
        }
    
    }
    
    public void conectarM(){
        String cadenaC="jdbc:mysql://127.0.0.1:3306/java_conex?"+"user=root"+"&"+"password=18030asgo";//CADENA DE CONEXION COMPLETA :)
        try{
            Class.forName("org.gjt.mm.mysql.Driver");// TIPO DE CONEXION
            conn=DriverManager.getConnection(cadenaC);
           // JOptionPane.showMessageDialog(null,"Conectado!");
        }catch (Exception e){
             JOptionPane.showMessageDialog(null, e);
          
            conn=null;
        }
        
    }
    
    
    public ResultSet Consulta(String consulta){
        
        try{
            Statement sentencia = null;
                sentencia = conn.createStatement();
                resultado= sentencia.executeQuery(consulta);
                return resultado; 

        } catch (SQLException ex){
                System.err.print(ex.getMessage());
        }
        return null;
    }
   
    
  public void log(String usuario, String password){
        String cadenaC="jdbc:postgresql://127.0.0.1/apli_usuario?"+"user="+usuario+"&password="+password;
        try{
            Class.forName("org.postgresql.Driver");// TIPO DE CONEXION
            
            conn=DriverManager.getConnection(cadenaC);
            if(!conn.isClosed()){
              JOptionPane.showMessageDialog(null,"Bienvenido! "+usuario);  
            }
          // JOptionPane.showMessageDialog(null,"Conectado!");
        }catch (Exception e){
             JOptionPane.showMessageDialog(null,"ERROR, USUARIO Y CONTRACEÑA"+"\n"+e);
            conn=null;
        }
   } 
   
}
