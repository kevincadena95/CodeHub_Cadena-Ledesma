package com.itsqmet.codehub.service;

import com.itsqmet.codehub.model.Empleado;
import com.itsqmet.codehub.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> obtenerTodo() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> buscarporId(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public boolean eliminar(Long id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);

        if (empleado.isPresent()) {
            empleadoRepository.delete(empleado.get());
            return true;
        }
        return false;
    }

    public Optional<Empleado> actualizar(Long id, Empleado empleadoActualizado) {
        return empleadoRepository.findById(id).map(empleado -> {
            empleado.setCedula(empleadoActualizado.getCedula());
            empleado.setNombres(empleadoActualizado.getNombres());
            empleado.setApellidos(empleadoActualizado.getApellidos());
            empleado.setCorreo(empleadoActualizado.getCorreo());
            empleado.setCargo(empleadoActualizado.getCargo());
            empleado.setDepartamento(empleadoActualizado.getDepartamento());
            empleado.setSalario(empleadoActualizado.getSalario());

            return empleadoRepository.save(empleado);
        });
    }
}