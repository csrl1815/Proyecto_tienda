/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tienda;

import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author JordanStevenChavarri
 */
public class SeleccionProducto implements RegistroCliente {

    //atributos de producto
    protected int idProducto;
    protected int cantidadProducto;
    protected double totalPrecio;
    //atributos de cliente
    protected String nombreCliente;
    protected String apellidoCliente;
    protected String correo;

    public SeleccionProducto() {
    }

    @Override
    public void RegistroCliente() {
        try {
            nombreCliente = JOptionPane.showInputDialog("Ingresar el nombre del cliente");
            apellidoCliente = JOptionPane.showInputDialog("Ingresar el apellido del cliente");
            correo = JOptionPane.showInputDialog("Ingresar el correo del cliente");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Un error ha ocurrido " + e.getMessage(),"Error",JOptionPane.ERROR);
            RegistroCliente();
        }
        Validar();
    }

    @Override
    public void Validar() {
        try {
            int opcionSeleccionada = JOptionPane.showConfirmDialog(null, "Nombre " + nombreCliente + "\nApellido: " + apellidoCliente + "\nCorreo: " + correo + "\nLa información esta correcta?", "Validación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcionSeleccionada == 0) {
                JOptionPane.showMessageDialog(null, "Información Valida");
            } else {
                RegistroCliente();
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Un error ha ocurrido "+ e.getMessage(),"Error",JOptionPane.ERROR );
            RegistroCliente();
        }
    }

    public void agregar() {
        RegistroCliente();
        try {
            DataOutputStream archivo = new DataOutputStream(
                    new FileOutputStream("Productos.dat", true));
            idProducto = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Ingrese el ID del producto"));
            cantidadProducto = Integer.parseInt(JOptionPane.showInputDialog(null, "Cuantas unidades?"));

            archivo.writeInt(idProducto);
            archivo.writeInt(cantidadProducto);
            archivo.writeUTF(nombreCliente);
            archivo.writeUTF(apellidoCliente);
            archivo.writeUTF(correo);

            JOptionPane.showMessageDialog(null, "Datos agregados correctamente!",
                    "Datos agregados", JOptionPane.INFORMATION_MESSAGE);
            archivo.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
    //busca en el carrito el producto por nombre de cliente
    public void buscarPorNombre() {
        String nombreCliente, apellidoCliente, correo;
        int cantidadProducto, idProducto;

        String nombreIngresado = JOptionPane.showInputDialog(null, "Digite el nombre a "
                + "buscar:");

        try {
            DataInputStream archivo = new DataInputStream(
                    new FileInputStream("Productos.dat"));
            try {
                while (true) {
                    nombreCliente = archivo.readUTF();
                    apellidoCliente = archivo.readUTF();
                    correo = archivo.readUTF();
                    cantidadProducto = archivo.readInt();
                    idProducto = archivo.readInt();
                    
                    if (nombreIngresado.equals(nombreCliente)) {
                        System.out.println("Se ha agregado al carrito de "+nombreCliente+" "+apellidoCliente+", "+cantidadProducto+" unidades del producto con ID "+idProducto);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado ningúna coincidencia");
                    }
                }
            } catch (EOFException ex) {
                archivo.close();
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,
                    "El archivo no existe", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error Desconocido", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

//Encapsuladores
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public double getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(double totalPrecio) {
        this.totalPrecio = totalPrecio;
    }

}
