/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import Modelo.ClaseDto;
import Modelo.ConexionBD;
import Modelo.Entrenador;
import Modelo.EntrenadorDto;
import Modelo.TiposEspecialidades;
import Modelo.Tipos_de_Clases;
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
public class EntrenadorDAO {
   private Connection getConnection() {
        return ConexionBD.getInstancia().getConexion();
    }
    
    public void agregar(EntrenadorDto dto){
        try {
            Connection cn = getConnection();
            
            // Si el ID es 0, generar uno automáticamente
            int idFinal = dto.getId();
            if (idFinal == 0) {
                idFinal = obtenerSiguienteId();
            }
            
            PreparedStatement ps = cn.prepareStatement("INSERT INTO entrenadores (id, nombre, contacto, especialidad) VALUES (?, ?, ?, ?)");
            ps.setInt(1, idFinal);
            ps.setString(2, dto.getNombre());
            ps.setString(3, dto.getContacto());
            ps.setString(4, dto.getEspecialidad().name()); 
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }
    }
    
    // Método para obtener el siguiente ID disponible
    private int obtenerSiguienteId() {
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement("SELECT COALESCE(MAX(id), 0) + 1 AS siguiente_id FROM entrenadores");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("siguiente_id");
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener siguiente ID: " + ex);
        }
        return 1; // Si hay error, devolver 1 como valor por defecto
    }
    
    public void actualizar(EntrenadorDto dto){
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement("UPDATE entrenadores SET nombre = ?, contacto = ?, especialidad = ? WHERE id = ?");
            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getContacto());
            ps.setString(3, dto.getEspecialidad().name());
            ps.setInt(4, dto.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }
    }
    
    public EntrenadorDto buscarEntrenadorPorId(int idEntrenador){
        try {
          Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement("SELECT id, nombre, contacto, especialidad FROM entrenadores WHERE id = ?");
            
            ps.setInt(1, idEntrenador);
            ResultSet rs= ps.executeQuery();
                if (rs.next()) {
                    // 1. Obtener String de la especialidad desde la DB
                    String especialidadDB = rs.getString("especialidad");
                    
                    // 2. Convertir el String a la constante Enum
                    // NOTA: Asumiendo que TiposEspecialidades.valueOf funciona (el String de la DB coincide con el nombre del Enum)
                    TiposEspecialidades especialidadEnum = TiposEspecialidades.valueOf(especialidadDB.toUpperCase());
                    
                    // 3. Devolver el objeto DTO
                    return new EntrenadorDto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contacto"),
                        especialidadEnum
                    );
}
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }
        return null; 
    }
    

    public void eliminar(int id){ 
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement("DELETE FROM entrenadores WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }
    }
    
    public List<EntrenadorDto> obtenerTodo(){ 
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement("SELECT id, nombre, contacto, especialidad FROM entrenadores");
            ResultSet rs= ps.executeQuery();
            List<EntrenadorDto> list=new ArrayList(); 
            while (rs.next()){
               String especialidadDB = rs.getString("especialidad");
                    TiposEspecialidades especialidadEnum = TiposEspecialidades.valueOf(especialidadDB.toUpperCase());
                    
                    EntrenadorDto entrenador = new EntrenadorDto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contacto"),
                        especialidadEnum
                    );
                    list.add(entrenador);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }
        return null;
    }
}

