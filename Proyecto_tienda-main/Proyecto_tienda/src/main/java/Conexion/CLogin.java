/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Tienda.menuEmpleado;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author axelc
 */
public class CLogin {
    
    public void validaUsuario(JTextField usuario, JPasswordField pass){
        
        try {
        //conexiones de credenciales a form
            ResultSet rs = null;
            PreparedStatement ps = null;
            
            Conexion.ConexionBD conectLogin = new Conexion.ConexionBD();
            
            String consulta="select * from usuarios where usuarios.ingresoUsuario = (?) and usuarios.ingresoPass = (?)";
            ps = conectLogin.estableceConexion().prepareStatement(consulta);
            
            String contra = String.valueOf(pass.getPassword());
            ps.setString(1, usuario.getText());
            ps.setString(2, contra);
        //ejecuta consulta sobre los credenciales solicitados    
            rs = ps.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "El usuario es correcto");
                menuEmpleado mEmpleado = new menuEmpleado();
                mEmpleado.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "El usuario es incorrecto, vuelva a intentar");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: "+ e.toString());
        }
    }
}
