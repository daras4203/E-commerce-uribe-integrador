package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.servicios.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Controlador para operaciones en la tabla productos")
public class ProductoControlador {

    @Autowired
    private ProductoServicio servicio;

    // Guardar producto
    @Operation(summary = "Crear un producto en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ProductoDTO> guardar(@RequestBody Producto datos) {
        ProductoDTO respuesta = this.servicio.guardarProducto(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //  Listar todos los productos
    @Operation(summary = "Listar todos los productos guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ProductoDTO>> listar() {
        List<ProductoDTO> respuesta = this.servicio.buscarTodosLosProductos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //  Buscar producto por ID
    @Operation(summary = "Buscar un producto por ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductoDTO> buscarPorId(@PathVariable Integer id) {
        ProductoDTO respuesta = this.servicio.buscarProductoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Buscar productos por categoría
    @Operation(summary = "Buscar productos por categoría")
    @GetMapping(value = "/categoria/{categoria}", produces = "application/json")
    public ResponseEntity<List<ProductoDTO>> buscarPorCategoria(@PathVariable String categoria) {
        List<ProductoDTO> respuesta = this.servicio.buscarProductosPorCategoria(categoria);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Buscar productos por precio
    @Operation(summary = "Buscar productos por precio exacto")
    @GetMapping(value = "/precio/{precio}", produces = "application/json")
    public ResponseEntity<List<ProductoDTO>> buscarPorPrecio(@PathVariable Double precio) {
        List<ProductoDTO> respuesta = this.servicio.buscarProductosPorPrecio(precio);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //  Actualizar producto
    @Operation(summary = "Actualizar un producto existente")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Integer id, @RequestBody Producto datos) {
        ProductoDTO respuesta = this.servicio.actualizarProducto(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //  Eliminar producto
    @Operation(summary = "Eliminar un producto por ID")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}

