package com.itsqmet.codehub.controller;

import com.itsqmet.codehub.model.PerfilEmpleado;
import com.itsqmet.codehub.service.PerfilEmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/perfiles-empleados")
@CrossOrigin("*")
public class PerfilEmpleadoController {

    @Autowired
    private PerfilEmpleadoService perfilEmpleadoService;

    @GetMapping
    public ResponseEntity<List<PerfilEmpleado>> obtenerTodos() {
        List<PerfilEmpleado> perfilesEmpleados = perfilEmpleadoService.obtenerTodo();
        return ResponseEntity.ok(perfilesEmpleados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return perfilEmpleadoService.buscarporId(id)
                .map(perfilEmpleado -> ResponseEntity.ok((Object) perfilEmpleado))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Perfil de empleado con id " + id + " no encontrado")));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody PerfilEmpleado perfilEmpleado, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        PerfilEmpleado nuevo = perfilEmpleadoService.crearPerfilEmpleado(perfilEmpleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (perfilEmpleadoService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Perfil de empleado eliminado correctamente"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Perfil de empleado con id " + id + " no encontrado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody PerfilEmpleado perfilEmpleado,
                                        BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        return perfilEmpleadoService.actualizar(id, perfilEmpleado)
                .map(actualizado -> ResponseEntity.ok((Object) actualizado))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Perfil de empleado con id " + id + " no encontrado")));
    }
}