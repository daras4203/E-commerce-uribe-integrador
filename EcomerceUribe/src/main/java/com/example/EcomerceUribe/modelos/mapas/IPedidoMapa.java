package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPedidoMapa {

    // Se construyen 2 funciones por mapa

    // 1. Que transforme 1 modelo en 1 DTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "montoTotal", target = "montoTotal")
    @Mapping(source = "fechaCreacion", target = "fechaCreacion")
    @Mapping(source = "fechaEntrega", target = "fechaEntrega")
    @Mapping(source = "costoEnvio", target = "costoEnvio")
    @Mapping(source = "productos", target = "productos")
    public PedidoDTO convertir_pedido_a_pedidodto(Pedido pedido);

    // 2. Que transforme una List<modelo> en una List<DTO>
    List<PedidoDTO> convertir_lista_a_listadto(List<Pedido> lista);

    List<PedidoDTO> convertir_lista_a_listadtos(List<Pedido> listaPedidos);
}
