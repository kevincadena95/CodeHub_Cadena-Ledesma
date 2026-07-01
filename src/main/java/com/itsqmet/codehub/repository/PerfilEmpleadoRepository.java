package com.itsqmet.codehub.repository;

import com.itsqmet.codehub.model.PerfilEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilEmpleadoRepository extends JpaRepository<PerfilEmpleado, Long> {

    Optional<PerfilEmpleado> findById(Long id);
}
