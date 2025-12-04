package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
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
@Tag(name="controlador para operaciones en la tabla pedidos")
public class PedidoControlador {

    @Autowired
    private PedidoServicio servicio;

    @Operation(summary = "Crear un pedido en la base de datos")
    @PostMapping(produces = "application/json")
    public ResponseEntity<PedidoDTO> guardar(@RequestBody Pedido datos) {
        PedidoDTO respuesta = servicio.guardarPedido(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Operation(summary = "Listar todos los pedidos guardados en la base de datos")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PedidoDTO>> listar() {
        List<PedidoDTO> respuesta = servicio.buscarTodosLosPedidos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Buscar un pedido por ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Integer id) {
        PedidoDTO respuesta = servicio.buscarPedidoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Eliminar un pedido según fecha de creación")
    @DeleteMapping(value = "/fecha/{fechaCreacion}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable LocalDate fechaCreacion) {
        servicio.eliminarPedido(fechaCreacion);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Modificar monto y fecha de creación de un pedido")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PedidoDTO> modificar(@PathVariable Integer id, @RequestBody Pedido datos) {
        PedidoDTO respuesta = servicio.actualizarPedido(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}




