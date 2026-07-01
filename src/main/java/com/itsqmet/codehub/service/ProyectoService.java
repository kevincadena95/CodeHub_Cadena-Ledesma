package com.itsqmet.codehub.service;

import com.itsqmet.codehub.model.Proyecto;
import com.itsqmet.codehub.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    public List<Proyecto> obtenerTodo() {
        return proyectoRepository.findAll();
    }

    public Optional<Proyecto> buscarPorId(Long id) {
        return proyectoRepository.findById(id);
    }

    public Proyecto crearProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public Optional<Proyecto> actualizar(Long id, Proyecto proyectoActualizado) {
        return proyectoRepository.findById(id).map(proyecto -> {
            proyecto.setNombre(proyectoActualizado.getNombre());
            proyecto.setDescripcion(proyectoActualizado.getDescripcion());
            proyecto.setEstado(proyectoActualizado.getEstado());
            proyecto.setFechaInicio(proyectoActualizado.getFechaInicio());
            proyecto.setPresupuesto(proyectoActualizado.getPresupuesto());
            return proyectoRepository.save(proyecto);
        });
    }

    public boolean eliminar(Long id) {
        if (proyectoRepository.existsById(id)) {
            proyectoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}