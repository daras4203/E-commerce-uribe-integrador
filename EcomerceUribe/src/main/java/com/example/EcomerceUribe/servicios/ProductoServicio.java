package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.mapas.IProductoMapa;
import com.example.EcomerceUribe.repositorios.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductoServicio {

    @Autowired
    private IProductoRepositorio repositorio;

    @Autowired
    private IProductoMapa mapa;

    // Guardar producto
    public ProductoDTO guardarProducto(Producto datosProducto) {

        // ✅ Validar que el producto tenga una categoría asociada
        if (datosProducto.getCategoria() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El producto debe tener una categoría asociada"
            );
        }

        // Guardar en base de datos
        Producto productoGuardado = this.repositorio.save(datosProducto);

        if (productoGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el producto"
            );
        }

        // Retornar DTO convertido
        return this.mapa.convertir_producto_a_productodto(productoGuardado);
    }
}
