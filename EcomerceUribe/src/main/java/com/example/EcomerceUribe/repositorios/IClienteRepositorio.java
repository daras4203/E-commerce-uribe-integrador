package com.example.EcomerceUribe.repositorios;

import com.example.EcomerceUribe.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IClienteRepositorio extends JpaRepository<Cliente, Integer> {

    // GUARDAR
    // EDITAR POR ID
    // ELIMINAR POR ID
    // BUSCAR POR ID
    // BUSCAR TODOS LOS REGISTROS

    // SECCIÓN DE CONSULTAS PERSONALIZADAS
    List<Cliente> findByNombre(String nombre);
    Optional<Cliente> findByDireccion(String direccion);
    List<Cliente> findByDepartamento(String departamento);
}
