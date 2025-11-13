package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.servicios.EmpleadoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@Tag(name = "Controlador para operaciones en la tabla empleados")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoServicio servicio;

    //  Crear empleado
    @Operation(summary = "Crear un empleado en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<EmpleadoDTO> guardar(@RequestBody Empleado datos) {
        EmpleadoDTO respuesta = this.servicio.guardarEmpleado(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //  Listar todos los empleados
    @Operation(summary = "Listar todos los empleados guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<EmpleadoDTO>> listar() {
        List<EmpleadoDTO> respuesta = this.servicio.buscarTodosLosEmpleados();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //  Buscar empleado por ID
    @Operation(summary = "Buscar un empleado por ID en la BD")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EmpleadoDTO> buscarPorId(@PathVariable Integer id) {
        EmpleadoDTO respuesta = this.servicio.buscarEmpleadoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }


    //  Actualizar empleado
    @Operation(summary = "Actualizar los datos de un empleado")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EmpleadoDTO> actualizar(@PathVariable Integer id, @RequestBody Empleado datos) {
        EmpleadoDTO respuesta = this.servicio.actualizarEmpleado(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //  Eliminar empleado
    @Operation(summary = "Eliminar un empleado por ID")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}
