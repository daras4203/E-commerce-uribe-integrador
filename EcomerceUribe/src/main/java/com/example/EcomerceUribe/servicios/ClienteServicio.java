package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
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
public class ClienteServicio {

    @Autowired
    private IClienteRepositorio repositorio;

    @Autowired
    private IClienteMapa mapa;

    // 1Guardar cliente
    public ClienteDTO guardarCliente(Cliente datosCliente) {

        // Validar si ya existe un cliente con el mismo ID (si viene definido)
        if (datosCliente.getId() != null && repositorio.findById(datosCliente.getId()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un cliente registrado con el ID ingresado"
            );
        }

        // Validar si ya existe cliente con la misma dirección
        if (datosCliente.getDireccion() != null &&
                repositorio.findByDireccion(datosCliente.getDireccion()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un cliente registrado con la dirección ingresada"
            );
        }

        // Intentar guardar
        Cliente clienteGuardado = repositorio.save(datosCliente);
        if (clienteGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el cliente"
            );
        }

        return mapa.convertir_cliente_a_clientedto(clienteGuardado);
    }

    //  Buscar todos los clientes
    public List<ClienteDTO> buscarTodosLosClientes() {
        List<Cliente> listaClientes = repositorio.findAll();
        return mapa.convertir_lista_a_listadtocliente(listaClientes);
    }

    //  Buscar cliente por ID
    public ClienteDTO buscarClientePorId(Integer id) {
        Optional<Cliente> clienteBuscado = repositorio.findById(id);
        if (clienteBuscado.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró cliente con el ID " + id
            );
        }
        return mapa.convertir_cliente_a_clientedto(clienteBuscado.get());
    }

    //  Buscar clientes por nombre
    public List<ClienteDTO> buscarClientesPorNombre(String nombre) {
        List<Cliente> clientes = repositorio.findByNombre(nombre);
        if (clientes.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron clientes con el nombre " + nombre
            );
        }
        return mapa.convertir_lista_a_listadtocliente(clientes);
    }

    //  Buscar clientes por departamento
    public List<ClienteDTO> buscarClientesPorDepartamento(String departamento) {
        List<Cliente> clientes = repositorio.findByDepartamento(departamento);
        if (clientes.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron clientes en el departamento " + departamento
            );
        }
        return mapa.convertir_lista_a_listadtocliente(clientes);
    }

    //  Actualizar cliente
    public ClienteDTO actualizarCliente(Integer id, Cliente datosActualizados) {
        Optional<Cliente> clienteBuscado = repositorio.findById(id);
        if (clienteBuscado.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró cliente con el ID " + id
            );
        }

        Cliente cliente = clienteBuscado.get();



        Cliente clienteActualizado = repositorio.save(cliente);
        if (clienteActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el cliente"
            );
        }

        return mapa.convertir_cliente_a_clientedto(clienteActualizado);
    }

    //  Eliminar cliente
    public void eliminarCliente(Integer id) {
        Optional<Cliente> clienteBuscado = repositorio.findById(id);
        if (clienteBuscado.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró cliente con el ID " + id
            );
        }

        try {
            repositorio.delete(clienteBuscado.get());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al eliminar el cliente: " + e.getMessage()
            );
        }
    }
}
