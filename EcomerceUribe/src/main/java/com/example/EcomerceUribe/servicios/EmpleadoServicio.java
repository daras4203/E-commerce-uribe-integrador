package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.EcomerceUribe.repositorios.IEmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmpleadoServicio {

    @Autowired
    private IEmpleadoRepositorio repositorio;

    @Autowired
    private IEmpleadoMapa mapa;

    // Guardar empleado
    public EmpleadoDTO guardarEmpleado(Empleado datosEmpleado) {

        if (this.repositorio.findById(datosEmpleado.getId()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un empleado registrado con el ID ingresado"
            );
        }

        Empleado empleadoGuardado = this.repositorio.save(datosEmpleado);

        if (empleadoGuardado == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el empleado");
        }

        return this.mapa.convertir_empleado_a_empleadodto(empleadoGuardado);
    }
}
