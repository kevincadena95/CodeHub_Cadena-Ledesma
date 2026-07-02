package com.itsqmet.codehub.controller;

import com.itsqmet.codehub.model.Proyecto;
import com.itsqmet.codehub.service.ProyectoReporteService;
import com.itsqmet.codehub.service.ProyectoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private ProyectoReporteService proyectoReporteService;

    @GetMapping
    public ResponseEntity<List<Proyecto>> obtenerTodo() {
        List<Proyecto> proyectos = proyectoService.obtenerTodo();
        return ResponseEntity.ok(proyectos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return proyectoService.buscarPorId(id)
                .map(proyecto -> ResponseEntity.ok((Object) proyecto))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Proyecto con id " + id + " no encontrado")));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Proyecto proyecto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();

            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        Proyecto nuevo = proyectoService.crearProyecto(proyecto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody Proyecto proyecto,
                                        BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();

            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        return proyectoService.actualizar(id, proyecto)
                .map(actualizado -> ResponseEntity.ok((Object) actualizado))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Proyecto con id " + id + " no encontrado")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (proyectoService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Proyecto eliminado correctamente"));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Proyecto con id " + id + " no encontrado"));
    }

    @GetMapping("/reporte/pdf")
    public ResponseEntity<byte[]> generarReportePdf() {
        byte[] pdf = proyectoReporteService.generarReporteProyectosPdf();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=reporte_proyectos.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}