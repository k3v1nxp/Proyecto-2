/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import Modelo.PagoDto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */

public class PagoDAO {

    private final String url;
    private final String user;
    private final String password;

    public PagoDAO() {
        this.url = "Agregar";
        this.user = "Agregar";
        this.password = "Agregar";
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    
     public void agregar(PagoDto dto) {
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(
                "INSERT INTO pago (subtotal, impuesto, total, monto, id_cliente, fecha) "
                + "VALUES (?, ?, ?, ?, ?, ?)"
            );

            ps.setDouble(1, dto.getSubtotal());
            ps.setDouble(2, dto.getImpuesto());
            ps.setDouble(3, dto.getTotal());
            ps.setInt(4, dto.getMonto());
            ps.setInt(5, dto.getIdCliente());
            ps.setDate(6, new Date(dto.getFecha().getTime()));

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void actualizar(PagoDto dto) {
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(
                "UPDATE pago SET subtotal=?, impuesto=?, total=?, monto=?, id_cliente=?, fecha=? "
                + "WHERE id_pago=?"
            );

            ps.setDouble(1, dto.getSubtotal());
            ps.setDouble(2, dto.getImpuesto());
            ps.setDouble(3, dto.getTotal());
            ps.setInt(4, dto.getMonto());
            ps.setInt(5, dto.getIdCliente());
            ps.setDate(6, new Date(dto.getFecha().getTime()));
            ps.setInt(7, dto.getId_pago());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

 
    public PagoDto buscar(int id) {
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(
                "SELECT id_pago, subtotal, impuesto, total, monto, id_cliente, fecha "
                + "FROM pago WHERE id_pago=?"
            );

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new PagoDto(
                    rs.getInt("id_pago"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("impuesto"),
                    rs.getDouble("total"),
                    rs.getInt("monto"),
                    rs.getInt("id_cliente"),
                    rs.getDate("fecha")
                );
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return null;
    }

    public void eliminar(int id) {
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(
                "DELETE FROM pago WHERE id_pago=?"
            );

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public List<PagoDto> obtenerTodo() {
        List<PagoDto> lista = new ArrayList<>();

        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(
                "SELECT id_pago, subtotal, impuesto, total, monto, id_cliente, fecha FROM pago"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                PagoDto dto = new PagoDto(
                    rs.getInt("id_pago"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("impuesto"),
                    rs.getDouble("total"),
                    rs.getInt("monto"),
                    rs.getInt("id_cliente"),
                    rs.getDate("fecha")
                );

                lista.add(dto);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return lista;
    }
}
    


