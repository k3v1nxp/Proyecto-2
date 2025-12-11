/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;
import Modelo.ConexionBD;
import Modelo.T_Rol;
import Modelo.UsuarioDto;
import java.sql.Connection;
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
public class UsuarioDAO {
    
    private Connection getConnection() {
        return ConexionBD.getInstancia().getConexion();
    }
    
     public void agregar(UsuarioDto dto) {
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(
    "INSERT INTO usuario (usuario, contraseña, rol, activo) " 
    + "VALUES (?, ?, ?, ?)"
    );// se usa asi por que la idea es que el id se autogenere
            
            ps.setString(1, dto.getUsuario());
            ps.setString(2, dto.getUsuario());
            ps.setString(3, dto.getContraseña());
            ps.setString(4, dto.getRol().name());
            ps.setBoolean(5, dto.isActivo());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }
     public void actualizar(UsuarioDto dto) {
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(
                "UPDATE usuario SET usuario=?, contraseña=?, rol=?, activo=? "
                + "WHERE id=?"
            );

            ps.setString(1, dto.getUsuario());
            ps.setString(2, dto.getContraseña());
            ps.setString(3, dto.getRol().name());
            ps.setBoolean(4, dto.isActivo());
            ps.setInt(5, dto.getId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
     }

    public UsuarioDto buscar(int id) {

        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(
                "SELECT id, usuario, contraseña, rol, activo "
                + "FROM usuario WHERE id=?"
            );

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new UsuarioDto(
                    rs.getInt("id"),
                    rs.getString("usuario"),
                    rs.getString("contraseña"),
                    T_Rol.valueOf(rs.getString("rol")),
                    rs.getBoolean("activo")
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
                "DELETE FROM usuario WHERE id=?"
            );

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public List<UsuarioDto> obtenerTodo() {
        List<UsuarioDto> lista = new ArrayList<>();

        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(
                "SELECT id, usuario, contraseña, rol, activo FROM usuario"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                UsuarioDto dto = new UsuarioDto(
                    rs.getInt("id"),
                    rs.getString("usuario"),
                    rs.getString("contraseña"),
                    T_Rol.valueOf(rs.getString("rol")),
                    rs.getBoolean("activo")
                );

                lista.add(dto);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return lista;
    }
}
