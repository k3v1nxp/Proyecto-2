/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author UTN
 */
public class RepositorioCliente {
     private final Map<Integer, Cliente> data = new HashMap<>();
    
    public void guardar(Cliente c){
        data.put(c.getId(), c);
    }
    
    public Optional<Cliente> buscar(int id){
        return Optional.ofNullable(data.get(id));
    }
    
    public void eliminar(int id){ 
        data.remove(id);
    }
    
    public List<Cliente> obtenerTodo(){ 
        return new ArrayList<>(data.values()); 
    }
}
