package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.servicios.ClienteServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Controlador para operaciones en la tabla clientes")
public class ClienteControlador {

    @Autowired
    private ClienteServicio servicio;

    // Crear cliente
    @Operation(summary = "Registrar un nuevo cliente en la base de datos")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ClienteDTO> guardar(@RequestBody Cliente datos) {
        ClienteDTO respuesta = this.servicio.guardarCliente(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    // Listar todos los clientes
    @Operation(summary = "Listar todos los clientes registrados")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ClienteDTO>> listar() {
        List<ClienteDTO> respuesta = this.servicio.buscarTodosLosClientes();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Buscar cliente por ID
    @Operation(summary = "Buscar un cliente por su ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Integer id) {
        ClienteDTO respuesta = this.servicio.buscarClientePorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }


    // Actualizar cliente
    @Operation(summary = "Actualizar los datos de un cliente existente")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Integer id, @RequestBody Cliente datos) {
        ClienteDTO respuesta = this.servicio.actualizarCliente(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Eliminar cliente
    @Operation(summary = "Eliminar un cliente por ID")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}

