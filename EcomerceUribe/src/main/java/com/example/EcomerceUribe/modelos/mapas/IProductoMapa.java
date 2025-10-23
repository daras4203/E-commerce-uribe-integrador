package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductoMapa {

    // Se construyen 2 funciones por mapa

    // 1. Que transforme 1 modelo en 1 DTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "fotografia", target = "fotografia")
    @Mapping(source = "descripcion", target = "descripcion")
    @Mapping(source = "categoria", target = "categoria")
    @Mapping(source = "precioUnitario", target = "precioUnitario")
    @Mapping(source = "marca", target = "marca")
    @Mapping(source = "aplicaDescuento", target = "aplicaDescuento")
    public ProductoDTO convertir_producto_a_productodto(Producto producto);

    // 2. Que transforme una List<modelo> en una List<DTO>
    List<ProductoDTO> convertir_lista_a_listadto(List<Producto> lista);
}
