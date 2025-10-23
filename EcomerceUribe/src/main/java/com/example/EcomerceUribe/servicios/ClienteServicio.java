package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteServicio {

    @Autowired
    private IClienteRepositorio repositorio;

    @Autowired
    private IClienteMapa mapa;

    // Guardar cliente
    public ClienteDTO guardarCliente(Cliente datosCliente) {

        // Validar si el ID ya existe en la base de datos
        if (this.repositorio.findById(datosCliente.getId()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un cliente registrado con el ID ingresado"
            );
        }

        // Intentar guardar el cliente
        Cliente clienteGuardado = this.repositorio.save(datosCliente);

        if (clienteGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el cliente"
            );
        }

        // Retornar DTO
        return this.mapa.convertir_cliente_a_clientedto(clienteGuardado);
    }
}

