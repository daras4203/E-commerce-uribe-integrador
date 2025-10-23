package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.mapas.IPedidoMapa;
import com.example.EcomerceUribe.repositorios.IPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PedidoServicio {

    @Autowired
    private IPedidoRepositorio repositorio;

    @Autowired
    private IPedidoMapa mapa;

    // Guardar pedido
    public PedidoDTO guardarPedido(Pedido datosPedido) {

        // Validar que la fecha de creación no sea nula
        if (datosPedido.getFechaCreacion() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La fecha de creación del pedido es obligatoria"
            );
        }

        Pedido pedidoGuardado = this.repositorio.save(datosPedido);

        if (pedidoGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el pedido"
            );
        }

        return this.mapa.convertir_pedido_a_pedidodto(pedidoGuardado);
    }
}
