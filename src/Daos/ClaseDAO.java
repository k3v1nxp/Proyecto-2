/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

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
public class ClaseDAO {
    private final String url;
    private final String user;
    private final String password;

    public ClaseDAO() {
        this.url = "Agregar";
        this.user = "Agregar";
        this.password = "Agregar";
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    
    public void agregar(ClaseDto dto){
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement("INSERT INTO categoria (id,nombre) VALUES (?,?)");
            ps.setInt(1, dto.getId());
            ps.setString(2, dto.getNombre());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }
    }
    
    public void actualizar(CategoriaDto dto){
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement("UPDATE categoria set nombre=? where id = ?");
            ps.setString(1, dto.getNombre());
            ps.setInt(2, dto.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }
    }
    
    public CategoriaDto buscar(int id){
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement("SELECT id,nombre FROM categoria WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
                return new CategoriaDto(rs.getInt(1),rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }
        return null; 
    }
    
    public void eliminar(int id){ 
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement("DELETE categoria From categoria where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }
    }
    
    public List<CategoriaDto> obtenerTodo(){ 
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement("SELECT id,nombre FROM categoria");
            ResultSet rs= ps.executeQuery();
            List<CategoriaDto> list=new ArrayList(); 
            while (rs.next()){
                list.add(new CategoriaDto(rs.getInt(1),rs.getString(2)));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }
        return null;
    }
}
