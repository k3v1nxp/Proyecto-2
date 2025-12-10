/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import Modelo.ClienteDto;
import Modelo.ConexionBD;
import Modelo.TipoMembresia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author kevin
 */
public class ClienteDAO {
    private Connection getConnection() {
        return ConexionBD.getInstancia().getConexion();
    }

    public void agregar(ClienteDto dto) {
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(
                "INSERT INTO clientes (id, nombre, membresia, fecha_nacimiento, numero, fecha_vencimiento) "
                + "VALUES (?, ?, ?, ?, ?, ?)"
            );

            ps.setInt(1, dto.getId());
            ps.setString(2, dto.getNombre());
            ps.setString(3, dto.getMenbresia().name());
            ps.setDate(4, new Date(dto.getFecha_nacimiento().getTime()));
            ps.setInt(5, dto.getNumero());

            if (dto.getFechaVencimientoMembresia() != null) {
                ps.setDate(6, new Date(dto.getFechaVencimientoMembresia().getTime()));
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void actualizar(ClienteDto dto) {
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(
                "UPDATE clientes SET nombre = ?, membresia = ?, fecha_nacimiento = ?, numero = ?, fecha_vencimiento = ? "
                + "WHERE id = ?"
            );

            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getMenbresia().name());
            ps.setDate(3, new Date(dto.getFecha_nacimiento().getTime()));
            ps.setInt(4, dto.getNumero());

            if (dto.getFechaVencimientoMembresia() != null) {
                ps.setDate(5, new Date(dto.getFechaVencimientoMembresia().getTime()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }

            ps.setInt(6, dto.getId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public ClienteDto buscar(int id) {
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(
                "SELECT id, nombre, membresia, fecha_nacimiento, numero, fecha_vencimiento "
                + "FROM clientes WHERE id = ?"
            );

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Date fechaV = rs.getDate("fecha_vencimiento");

                return new ClienteDto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    TipoMembresia.valueOf(rs.getString("membresia")),
                    rs.getDate("fecha_nacimiento"),
                    rs.getInt("numero"),
                    fechaV
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
                "DELETE FROM clientes WHERE id = ?"
            );

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public List<ClienteDto> obtenerTodo() {
        List<ClienteDto> lista = new ArrayList<>();

        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(
                "SELECT id, nombre, membresia, fecha_nacimiento, numero, fecha_vencimiento FROM clientes"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Date fechaV = rs.getDate("fecha_vencimiento");

                ClienteDto dto = new ClienteDto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    TipoMembresia.valueOf(rs.getString("membresia")),
                    rs.getDate("fecha_nacimiento"),
                    rs.getInt("numero"),
                    fechaV
                );

                lista.add(dto);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return lista;
    }
}
