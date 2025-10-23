package com.example.EcomerceUribe.repositorios;

import com.example.EcomerceUribe.modelos.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IEmpleadoRepositorio extends JpaRepository<Empleado, Integer> {

    // GUARDAR
    // EDITAR POR ID
    // ELIMINAR POR ID
    // BUSCAR POR ID
    // BUSCAR TODOS LOS REGISTROS

    // SECCIÓN DE CONSULTAS PERSONALIZADAS
    Optional<Empleado> findByDocumento(String documento);

}
