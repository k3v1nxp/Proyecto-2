/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.pkg2;

import Modelo.ConexionBD;
import java.sql.Connection;

/**
 *
 * @author kevin
 */
public class Proyecto2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection con = ConexionBD.getInstancia().getConexion();

        if (con != null) {
            System.out.println("La conexión funciona correctamente.");
        } else {
            System.out.println("No se pudo conectar.");
        }
    }
    
}
