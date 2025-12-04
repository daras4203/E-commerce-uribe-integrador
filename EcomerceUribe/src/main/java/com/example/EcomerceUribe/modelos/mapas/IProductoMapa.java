package com.example.EcomerceUribe.modelos.mapas;



import com.example.EcomerceUribe.modelos.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel= "spring")
public interface IProductoMapa<ProductoDTO> {

    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "descripcion", target = "descripcion")
    @Mapping(source = "categoria", target = " categoria")
    @Mapping(source = "precioUnitario", target = "precioUnitario")
    @Mapping(source = "marca", target = "marca")
    @Mapping(source = "aplicaDescuento", target = "aplicaDescuento")
    ProductoDTO convertir_producto_a_productoDTO(Producto producto);


    List<ProductoDTO> convetir_lista_a_listaproductoDTO(List<Producto> lista);
}
