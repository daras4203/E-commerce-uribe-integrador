package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.mapas.IPedidoMapa;
import com.example.EcomerceUribe.repositorios.IPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    //  Guardar pedido
    public PedidoDTO guardarPedido(Pedido datosPedido) {

        // Validar fecha de creación obligatoria
        if (datosPedido.getFechaCreacion() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La fecha de creación del pedido es obligatoria"
            );
        }

        // Intentar guardar
        Pedido pedidoGuardado = this.repositorio.save(datosPedido);
        if (pedidoGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el pedido"
            );
        }

        // Retornar DTO
        return this.mapa.convertir_pedido_a_pedidodto(pedidoGuardado);
    }

    //  Buscar todos los pedidos
    public List<PedidoDTO> buscarTodosLosPedidos() {
        List<Pedido> listaPedidos = this.repositorio.findAll();
        if (listaPedidos.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No hay pedidos registrados"
            );
        }
        return this.mapa.convertir_lista_a_listadtos(listaPedidos);
    }

    //  Buscar pedido por ID
    public PedidoDTO buscarPedidoPorId(Integer id) {
        Optional<Pedido> pedidoBuscado = this.repositorio.findById(id);
        if (!pedidoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún pedido con el ID " + id
            );
        }

        return this.mapa.convertir_pedido_a_pedidodto(pedidoBuscado.get());
    }

    //  Buscar pedidos por fecha de creación
    public List<PedidoDTO> buscarPedidosPorFecha(LocalDate fechaCreacion) {
        if (fechaCreacion == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Debe proporcionar una fecha de creación para realizar la búsqueda"
            );
        }

        List<Pedido> pedidosPorFecha = this.repositorio.findByFechaCreacion(fechaCreacion);
        if (pedidosPorFecha.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron pedidos con la fecha: " + fechaCreacion
            );
        }

        return this.mapa.convertir_lista_a_listadtos(pedidosPorFecha);
    }

    //  Buscar pedidos por monto total
    public List<PedidoDTO> buscarPedidosPorMonto(Integer montoTotal) {
        if (montoTotal == null || montoTotal <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Debe proporcionar un monto total válido para realizar la búsqueda"
            );
        }

        List<Pedido> pedidosPorMonto = this.repositorio.findByMontoTotal(montoTotal);
        if (pedidosPorMonto.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron pedidos con el monto total: " + montoTotal
            );
        }

        return this.mapa.convertir_lista_a_listadtos(pedidosPorMonto);
    }

    //  Actualizar pedido
    public PedidoDTO actualizarPedido(Integer id, Pedido datosActualizados) {
        Optional<Pedido> pedidoBuscado = this.repositorio.findById(id);
        if (!pedidoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún pedido con el ID " + id
            );
        }

        Pedido pedido = pedidoBuscado.get();

        // Actualizaciones permitidas
        if (datosActualizados.getFechaCreacion() != null) {
            pedido.setFechaCreacion(datosActualizados.getFechaCreacion());
        }

        if (datosActualizados.getMontoTotal() != null && datosActualizados.getMontoTotal() > 0) {
            pedido.setMontoTotal(datosActualizados.getMontoTotal());
        }

        Pedido pedidoActualizado = this.repositorio.save(pedido);
        if (pedidoActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el pedido"
            );
        }

        return this.mapa.convertir_pedido_a_pedidodto(pedidoActualizado);
    }

    //  Eliminar pedido
    public void eliminarPedido(Integer id) {
        Optional<Pedido> pedidoBuscado = this.repositorio.findById(id);
        if (!pedidoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún pedido con el ID " + id
            );
        }

        try {
            this.repositorio.delete(pedidoBuscado.get());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al eliminar el pedido: " + e.getMessage()
            );
        }
    }
}
