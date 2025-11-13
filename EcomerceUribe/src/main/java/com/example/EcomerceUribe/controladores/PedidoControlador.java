package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.servicios.PedidoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Controlador para operaciones en la tabla pedidos")
public class PedidoControlador {

    @Autowired
    private PedidoServicio servicio;

    //  Guardar pedido
    @Operation(summary = "Crear un pedido en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<PedidoDTO> guardar(@RequestBody Pedido datos) {
        PedidoDTO respuesta = this.servicio.guardarPedido(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //  Listar todos los pedidos
    @Operation(summary = "Listar todos los pedidos guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PedidoDTO>> listar() {
        List<PedidoDTO> respuesta = this.servicio.buscarTodosLosPedidos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //  Buscar pedido por ID
    @Operation(summary = "Buscar un pedido por ID en la BD")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Integer id) {
        PedidoDTO respuesta = this.servicio.buscarPedidoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Buscar pedidos por fecha de creación
    @Operation(summary = "Buscar pedidos por fecha de creación")
    @GetMapping(value = "/fecha/{fecha}", produces = "application/json")
    public ResponseEntity<List<PedidoDTO>> buscarPorFecha(@PathVariable String fecha) {
        List<PedidoDTO> respuesta = this.servicio.buscarPedidosPorFecha(LocalDate.parse(fecha));
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Actualizar pedido
    @Operation(summary = "Actualizar un pedido existente en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PedidoDTO> actualizar(@PathVariable Integer id, @RequestBody Pedido datos) {
        PedidoDTO respuesta = this.servicio.actualizarPedido(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Eliminar pedido
    @Operation(summary = "Eliminar un pedido por ID")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}

