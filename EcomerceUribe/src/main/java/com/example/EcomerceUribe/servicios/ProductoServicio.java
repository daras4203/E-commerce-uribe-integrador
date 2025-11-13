package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.mapas.IProductoMapa;
import com.example.EcomerceUribe.repositorios.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {

    @Autowired
    private IProductoRepositorio repositorio;

    @Autowired
    private IProductoMapa mapa;

    //  Guardar producto
    public ProductoDTO guardarProducto(Producto datosProducto) {

        // Validar nombre obligatorio
        if (datosProducto.getNombre() == null || datosProducto.getNombre().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre del producto es obligatorio"
            );
        }

        // Validar categoría obligatoria
        if (datosProducto.getCategoria() == null ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El producto debe tener una categoría asociada"
            );
        }


        // Intentar guardar
        Producto productoGuardado = this.repositorio.save(datosProducto);
        if (productoGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el producto"
            );
        }

        // Retornar DTO
        return this.mapa.convertir_producto_a_productodto(productoGuardado);
    }

    //  Buscar todos los productos
    public List<ProductoDTO> buscarTodosLosProductos() {
        List<Producto> listaProductos = this.repositorio.findAll();
        if (listaProductos.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No hay productos registrados"
            );
        }
        return this.mapa.convertir_lista_a_listadtos(listaProductos);
    }

    //  Buscar producto por ID
    public ProductoDTO buscarProductoPorId(Integer id) {
        Optional<Producto> productoBuscado = this.repositorio.findById(id);
        if (!productoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún producto con el ID " + id
            );
        }

        return this.mapa.convertir_producto_a_productodto(productoBuscado.get());
    }

    //  Buscar productos por categoría (método personalizado)
    public List<ProductoDTO> buscarProductosPorCategoria(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Debe proporcionar una categoría para realizar la búsqueda"
            );
        }

        List<Producto> productosPorCategoria = this.repositorio.findByCategoria(categoria);
        if (productosPorCategoria.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron productos en la categoría: " + categoria
            );
        }

        return this.mapa.convertir_lista_a_listadtos(productosPorCategoria);
    }

    //  Buscar productos por precio (método personalizado)
    public List<ProductoDTO> buscarProductosPorPrecio(Double precio) {
        if (precio == null || precio <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Debe proporcionar un precio válido para realizar la búsqueda"
            );
        }

        List<Producto> productosPorPrecio = this.repositorio.findByPrecio(precio);
        if (productosPorPrecio.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron productos con el precio: " + precio
            );
        }

        return this.mapa.convertir_lista_a_listadtos(productosPorPrecio);
    }

    // Actualizar producto
    public ProductoDTO actualizarProducto(Integer id, Producto datosActualizados) {
        Optional<Producto> productoBuscado = this.repositorio.findById(id);
        if (!productoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún producto con el ID " + id
            );
        }

        Producto producto = productoBuscado.get();

        // Actualizaciones permitidas
        if (datosActualizados.getNombre() != null && !datosActualizados.getNombre().isBlank()) {
            producto.setNombre(datosActualizados.getNombre());
        }

        if (datosActualizados.getDescripcion() != null && !datosActualizados.getDescripcion().isBlank()) {
            producto.setDescripcion(datosActualizados.getDescripcion());
        }

        Producto productoActualizado = this.repositorio.save(producto);
        if (productoActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el producto"
            );
        }

        return this.mapa.convertir_producto_a_productodto(productoActualizado);
    }

    //  Eliminar producto
    public void eliminarProducto(Integer id) {
        Optional<Producto> productoBuscado = this.repositorio.findById(id);
        if (!productoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún producto con el ID " + id
            );
        }

        try {
            this.repositorio.delete(productoBuscado.get());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al eliminar el producto: " + e.getMessage()
            );
        }
    }
}
