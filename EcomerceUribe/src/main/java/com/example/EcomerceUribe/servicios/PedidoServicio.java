package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.mapas.IPedidoMapa;
import com.example.EcomerceUribe.repositorios.IPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServicio {

    @Autowired
    private IPedidoRepositorio repositorio;

    @Autowired
    private IPedidoMapa mapa;

    // Guardar pedido
    public PedidoDTO guardarPedido(Pedido datosPedido) {

        if (datosPedido.getMontoTotal() == null || datosPedido.getMontoTotal() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El monto total del pedido es obligatorio"
            );
        }

        Pedido pedidoGuardado = this.repositorio.save(datosPedido);

        if (pedidoGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el pedido en la base de datos"
            );
        }

        return (PedidoDTO) this.mapa.convertir_pedido_a_pedidodto(pedidoGuardado);
    }

    // Buscar todos los pedidos
    public List<PedidoDTO> buscarTodosLosPedidos() {
        List<Pedido> lista = this.repositorio.findAll();
        return this.mapa.convetir_lista_a_listapedidodto(lista);
    }

    // Buscar por ID
    public PedidoDTO buscarPedidoPorId(Integer id) {
        Optional<Pedido> pedidoBuscado = this.repositorio.findById(id);

        if (!pedidoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún pedido con el id " + id
            );
        }

        return (PedidoDTO) this.mapa.convertir_pedido_a_pedidodto(pedidoBuscado.get());
    }

    // Eliminar un pedido por fecha de creación
    public void eliminarPedido(LocalDate fechaCreacion) {
        List<Pedido> pedidos = this.repositorio.findByFechaCreacion(fechaCreacion);

        // CORREGIDO (la condición estaba al revés)
        if (pedidos.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún pedido con la fecha " + fechaCreacion
            );
        }

        Pedido pedidoEncontrado = pedidos.get(0);

        try {
            this.repositorio.delete(pedidoEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo eliminar el pedido: " + error.getMessage()
            );
        }
    }

    // Modificar pedido
    public PedidoDTO actualizarPedido(Integer id, Pedido datosActualizados) {
        Optional<Pedido> pedidoBuscado = this.repositorio.findById(id);

        if (!pedidoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún pedido con el id " + id
            );
        }

        Pedido pedidoEncontrado = pedidoBuscado.get();

        // Actualizo los campos permitidos
        pedidoEncontrado.setMontoTotal(datosActualizados.getMontoTotal());
        pedidoEncontrado.setFechaCreacion(datosActualizados.getFechaCreacion());

        Pedido pedidoActualizado = this.repositorio.save(pedidoEncontrado);

        if (pedidoActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el pedido en la base de datos"
            );
        }

        return (PedidoDTO) this.mapa.convertir_pedido_a_pedidodto(pedidoActualizado);
    }
}

