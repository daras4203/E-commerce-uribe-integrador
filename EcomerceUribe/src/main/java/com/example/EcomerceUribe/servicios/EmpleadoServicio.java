package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.EcomerceUribe.repositorios.IEmpleadoRepositorio;
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
public class EmpleadoServicio {

    @Autowired
    private IEmpleadoRepositorio repositorio;

    @Autowired
    private IEmpleadoMapa mapa;

    // Guardar empleado
    public EmpleadoDTO guardarEmpleado(Empleado datosEmpleado) {

        // Validar ID obligatorio
        if (datosEmpleado.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El ID del empleado es obligatorio"
            );
        }


        // Validar cargo obligatorio
        if (datosEmpleado.getCargo() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El cargo del empleado es obligatorio"
            );
        }


        // Intentar guardar
        Empleado empleadoGuardado = this.repositorio.save(datosEmpleado);
        if (empleadoGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el empleado"
            );
        }

        // Retornar DTO
        return this.mapa.convertir_empleado_a_empleadodto(empleadoGuardado);
    }

    //  Buscar todos los empleados
    public List<EmpleadoDTO> buscarTodosLosEmpleados() {
        List<Empleado> listaEmpleados = this.repositorio.findAll();
        return this.mapa.convertir_lista_a_listadtos(listaEmpleados);
    }

    //  Buscar empleado por ID
    public EmpleadoDTO buscarEmpleadoPorId(Integer id) {
        Optional<Empleado> empleadoBuscado = this.repositorio.findById(id);
        if (!empleadoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún empleado con el ID " + id
            );
        }

        return this.mapa.convertir_empleado_a_empleadodto(empleadoBuscado.get());
    }

    //  Buscar empleado por documento
    public EmpleadoDTO buscarEmpleadoPorDocumento(String documento) {
        Optional<Empleado> empleadoBuscado = this.repositorio.findByDocumento(documento);
        if (!empleadoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún empleado con el documento " + documento
            );
        }

        return this.mapa.convertir_empleado_a_empleadodto(empleadoBuscado.get());
    }

    //  Actualizar empleado
    public EmpleadoDTO actualizarEmpleado(Integer id, Empleado datosActualizados) {
        Optional<Empleado> empleadoBuscado = this.repositorio.findById(id);
        if (!empleadoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún empleado con el ID " + id
            );
        }

        Empleado empleado = empleadoBuscado.get();

        // Actualizaciones permitidas

        if (datosActualizados.getCargo() != null) {
            empleado.setCargo(datosActualizados.getCargo());
        }


        if (datosActualizados.getSalario() != null && datosActualizados.getSalario() >= 0) {
            empleado.setSalario(datosActualizados.getSalario());
        }

        Empleado empleadoActualizado = this.repositorio.save(empleado);
        if (empleadoActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el empleado"
            );
        }

        return this.mapa.convertir_empleado_a_empleadodto(empleadoActualizado);
    }

    //  Eliminar empleado
    public void eliminarEmpleado(Integer id) {
        Optional<Empleado> empleadoBuscado = this.repositorio.findById(id);
        if (!empleadoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún empleado con el ID " + id
            );
        }

        try {
            this.repositorio.delete(empleadoBuscado.get());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al eliminar el empleado: " + e.getMessage()
            );
        }
    }
}
