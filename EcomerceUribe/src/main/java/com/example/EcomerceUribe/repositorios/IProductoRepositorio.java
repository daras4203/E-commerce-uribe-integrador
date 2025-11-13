package com.example.EcomerceUribe.repositorios;

import com.example.EcomerceUribe.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IProductoRepositorio extends JpaRepository<Producto, Integer> {

    // GUARDAR
    // EDITAR POR ID
    // ELIMINAR POR ID
    // BUSCAR POR ID
    // BUSCAR TODOS LOS REGISTROS

    //  CONSULTAS PERSONALIZADAS
    List<Producto> findByCategoria(String categoria);// Buscar productos por categoría
    List<Producto> findByPrecio(Double precio); // Buscar por precios

}
