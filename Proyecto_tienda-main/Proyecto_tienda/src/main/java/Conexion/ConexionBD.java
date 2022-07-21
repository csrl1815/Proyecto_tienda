/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author axelc
 */
public class ConexionBD {
    
    Connection conectar = null;
    String username = "onlinestore@onlinestorefidelitas";
    String password = "Fidelitas2022$";
    String bd = "tienda_cliente_servidor";
    String ip= "onlinestorefidelitas.mysql.database.azure.com";
    String port = "3306";
   
    String cadena ="jdbc:mysql://"+ip+":"+port+"/"+bd;
    
    public Connection estableceConexion(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection(cadena, username, password);
            //JOptionPane.showMessageDialog(null, "Conexion satisfactoria");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se conecto a la base de datos, error"+ e.toString());
        }
        return conectar;
    }
}
