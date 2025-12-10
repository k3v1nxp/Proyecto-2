/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import Modelo.ClaseDto;
import Modelo.ConexionBD;
import Modelo.Entrenador;
import Modelo.EntrenadorDto;
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
public class ClaseDAO {
   
    private Connection getConnection() {
        return ConexionBD.getInstancia().getConexion();
    }
    
    public void agregar(ClaseDto dto){
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement("INSERT INTO clases (tipo, id_entrenador, horario, capacidad_maxima, capacidad_actual, activa) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, dto.getTipo()); // String del DTO
            ps.setInt(2, Integer.parseInt(dto.getidEntrenador())); 
            ps.setString(3, dto.getHorario());
            ps.setInt(4, dto.getCapacidadMaxima());
            ps.setInt(5, dto.getCapacidadActual());
            ps.setBoolean(6, true);
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }
    }
    
    public void actualizar(ClaseDto dto){
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement("UPDATE clases SET tipo = ?, id_entrenador = ?, horario = ?, capacidad_maxima = ?, capacidad_actual = ? WHERE id_clase = ?");
            ps.setString(1, dto.getTipo());
            ps.setInt(2, Integer.parseInt(dto.getidEntrenador()));
            ps.setString(3, dto.getHorario());
            ps.setInt(4, dto.getCapacidadMaxima());
            ps.setInt(5, dto.getCapacidadActual());
            ps.setInt(6, dto.getId_clase());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }
    }
    
    public ClaseDto buscar(int idClase){
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(  "SELECT id_clase, tipo, entrenador, horario, capacidad_maxima, capacidad_actual " +
            "FROM clases WHERE id_clase = ? AND activa = TRUE");
            
            ps.setInt(1, idClase);
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
              
                
                return new ClaseDto(
                    rs.getInt("id_clase"),
                    rs.getString("tipo"),
                    String.valueOf(rs.getInt("id_entrenador")),
                    rs.getString("horario"),
                    rs.getInt("capacidad_maxima"),
                    rs.getInt("capacidad_actual")
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
            PreparedStatement ps = cn.prepareStatement("DELETE clases From clases where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }
    }
    
    public List<ClaseDto> obtenerTodo(){ 
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement("SELECT id_clase, tipo, id_entrenador, horario, capacidad_maxima, capacidad_actual " +
            "FROM clases WHERE activa = TRUE");
            ResultSet rs= ps.executeQuery();
            List<ClaseDto> list=new ArrayList(); 
            while (rs.next()){
                ClaseDto dto = new ClaseDto(
                    rs.getInt("id_clase"),
                    rs.getString("tipo"),
                    String.valueOf(rs.getInt("id_entrenador")),
                    rs.getString("horario"),
                    rs.getInt("capacidad_maxima"),
                    rs.getInt("capacidad_actual")
                );

                list.add(dto);
            
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }
        return null;
    }
}
