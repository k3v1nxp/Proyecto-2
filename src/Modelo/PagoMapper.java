/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Mapper.IMapper;

/**
 *
 * @author kevin
 */
public class PagoMapper implements IMapper<Pago,PagoDto> {
    private Cliente clienteTemp;
    @Override
    public PagoDto ToDTO(Pago ent) {
         if (ent == null) return null;
    double total = ent.getTotal();
            return new PagoDto(
        ent.getId_pago(),
        ent.getSubtotal(),
        ent.getImpuesto(),
        total,                                             
        ent.getMonto(),
        ent.getCliente() != null ? ent.getCliente().getId() : 0,
        ent.getFecha()
    );
    }
    
    
    

    @Override
    public Pago ToEntidad(PagoDto dto) {
       if (clienteTemp == null)
            throw new IllegalStateException("Debe usar ToEntidad(dto, cliente).");

        return ToEntidad(dto, clienteTemp);
    }
       public Pago ToEntidad(PagoDto dto, Cliente cliente) {
        this.clienteTemp = cliente;  // almacenamos temporalmente
        return this.ToEntidad(dto);  // reutilizamos el método original
    }
    
}
