package com.itsqmet.codehub.repository;

import com.itsqmet.codehub.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByEmpresaContainingIgnoreCase(String empresa);
}