package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel= "spring")
public interface IPedidoMapa<PedidoDTO> {

    @Mapping(source = "montoTotal", target = "montoTotal")
    @Mapping(source = "fechaCreacion", target = "fechaCreacion")
    @Mapping(source = "fechaEntrega", target = "fechaEntrega")
    @Mapping(source = "costoEnvio", target = "costoEnvio")

    List<PedidoDTO> convetir_lista_a_listapedidodto(List<Pedido> lista);

    <PedidoDTO> PedidoDTO convertir_pedido_a_pedidodto(Pedido pedidoActualizado);
}

