/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tienda;

import Conexion.ConexionBD;

/**
 *
 * @author axelc
 */
public class Main {
    public static void main(String[] args) {
        ConexionBD conexion = new ConexionBD();
        conexion.estableceConexion();
        
        Login login = new Login();
        login.setVisible(true);
    }
}
