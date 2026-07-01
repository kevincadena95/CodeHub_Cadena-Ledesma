package com.itsqmet.codehub.repository;

import com.itsqmet.codehub.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findById(Long id);


}
