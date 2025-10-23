package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IClienteMapa {

    // Se construyen 2 funciones por mapa

    // 1. Que transforme 1 modelo en 1 DTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "ciudad", target = "ciudad")
    @Mapping(source = "referenciaPago", target = "referenciaPago")
    @Mapping(source = "calificacion", target = "calificacion")
    @Mapping(source = "departamento", target = "departamento")
    @Mapping(source = "usuario.id", target = "usuarioId")
    public ClienteDTO convertir_cliente_a_clientedto(Cliente cliente);

    // 2. Que transforme una List<modelo> en una List<DTO>
    List<ClienteDTO> convertir_lista_a_listadto(List<Cliente> lista);
}
