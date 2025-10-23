package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEmpleadoMapa {

    // Se construyen 2 funciones por mapa

    // 1. Que transforme 1 modelo en 1 DTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "cargo", target = "cargo")
    @Mapping(source = "salario", target = "salario")
    @Mapping(source = "fechaIngreso", target = "fechaIngreso")
    @Mapping(source = "area", target = "area")
    @Mapping(source = "usuario.id", target = "usuarioId")
    public EmpleadoDTO convertir_empleado_a_empleadodto(Empleado empleado);

    // 2. Que transforme una List<modelo> en una List<DTO>
    List<EmpleadoDTO> convertir_lista_a_listadto(List<Empleado> lista);
}
