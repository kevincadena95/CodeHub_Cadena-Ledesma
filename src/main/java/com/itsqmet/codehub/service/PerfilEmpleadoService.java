package com.itsqmet.codehub.service;

import com.itsqmet.codehub.model.PerfilEmpleado;
import com.itsqmet.codehub.repository.PerfilEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilEmpleadoService {

    @Autowired
    private PerfilEmpleadoRepository perfilEmpleadoRepository;

    public List<PerfilEmpleado> obtenerTodo() {
        return perfilEmpleadoRepository.findAll();
    }

    public Optional<PerfilEmpleado> buscarporId(Long id) {
        return perfilEmpleadoRepository.findById(id);
    }

    public PerfilEmpleado crearPerfilEmpleado(PerfilEmpleado perfilEmpleado) {
        return perfilEmpleadoRepository.save(perfilEmpleado);
    }

    public boolean eliminar(Long id) {
        if (perfilEmpleadoRepository.existsById(id)) {
            perfilEmpleadoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<PerfilEmpleado> actualizar(Long id, PerfilEmpleado perfilEmpleadoActualizado) {
        return perfilEmpleadoRepository.findById(id).map(perfilEmpleado -> {
            perfilEmpleado.setTelefonoPersonal(perfilEmpleadoActualizado.getTelefonoPersonal());
            perfilEmpleado.setDireccion(perfilEmpleadoActualizado.getDireccion());
            perfilEmpleado.setFechaContratacion(perfilEmpleadoActualizado.getFechaContratacion());
            perfilEmpleado.setEspecialidadTecnica(perfilEmpleadoActualizado.getEspecialidadTecnica());
            perfilEmpleado.setCertificaciones(perfilEmpleadoActualizado.getCertificaciones());
            perfilEmpleado.setModalidadTrabajo(perfilEmpleadoActualizado.getModalidadTrabajo());

            return perfilEmpleadoRepository.save(perfilEmpleado);
        });
    }
}