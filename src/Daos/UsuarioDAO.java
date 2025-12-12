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
                "INSERT INTO Usuario (usuario, contraseña, rol, activo) ");// se usa asi por que la idea es que el id se autogenere
            
            ps.setString(1, dto.getUsuario());
            ps.setString(2, dto.getContraseña());
            // Convertir el enum al formato de la BD
            String rolBD = convertirRolABD(dto.getRol());
            ps.setString(3, rolBD);
            ps.setBoolean(4, dto.isActivo());
        

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }
     public void actualizar(UsuarioDto dto) {
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(
                "UPDATE Usuario SET usuario=?, contraseña=?, rol=?, activo=? "
                + "WHERE id_usuario=?"
            );

            ps.setString(1, dto.getUsuario());
            ps.setString(2, dto.getContraseña());
            // Convertir el enum al formato de la BD
            String rolBD = convertirRolABD(dto.getRol());
            ps.setString(3, rolBD);
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
                "SELECT id_usuario, usuario, contraseña, rol, activo "
                + "FROM Usuario WHERE id_usuario=?"
            );

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String rolBD = rs.getString("rol");
                T_Rol rolEnum = convertirRolDesdeBD(rolBD);
                
                return new UsuarioDto(
                    rs.getInt("id_usuario"),
                    rs.getString("usuario"),
                    rs.getString("contraseña"),
                    rolEnum,
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
                "DELETE FROM Usuario WHERE id_usuario=?"
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
                "SELECT id_usuario, usuario, contraseña, rol, activo FROM Usuario"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String rolBD = rs.getString("rol");
                T_Rol rolEnum = convertirRolDesdeBD(rolBD);

                UsuarioDto dto = new UsuarioDto(
                    rs.getInt("id_usuario"),
                    rs.getString("usuario"),
                    rs.getString("contraseña"),
                    rolEnum,
                    rs.getBoolean("activo")
                );

                lista.add(dto);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return lista;
    }
    
    public UsuarioDto buscarPorUsuario(String nombreUsuario) {
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(
                "SELECT id_usuario, usuario, contraseña, rol, activo "
                + "FROM Usuario WHERE usuario = ? AND activo = 1"
            );

            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String rolBD = rs.getString("rol");
                T_Rol rolEnum = convertirRolDesdeBD(rolBD);
                
                return new UsuarioDto(
                    rs.getInt("id_usuario"),
                    rs.getString("usuario"),
                    rs.getString("contraseña"),
                    rolEnum,
                    rs.getBoolean("activo")
                );
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return null;
    }
    
    // Método para convertir el rol de la BD al enum
    private T_Rol convertirRolDesdeBD(String rolBD) {
        if (rolBD == null) {
            return T_Rol.ENTRENADOR; // Valor por defecto
        }
        
        // Convertir roles de la BD al enum
        if (rolBD.equalsIgnoreCase("Admin") || rolBD.equalsIgnoreCase("Administrador")) {
            return T_Rol.ADMINISTRADOR;
        } else if (rolBD.equalsIgnoreCase("Instructor") || rolBD.equalsIgnoreCase("Entrenador")) {
            return T_Rol.ENTRENADOR;
        }
        
        return T_Rol.ENTRENADOR; // Valor por defecto
    }
    
    // Método para convertir el enum al formato de la BD
    private String convertirRolABD(T_Rol rol) {
        if (rol == T_Rol.ADMINISTRADOR) {
            return "Admin";
        } else if (rol == T_Rol.ENTRENADOR) {
            return "Instructor";
        }
        return "Instructor"; // Valor por defecto
    }
}
