package com.itsqmet.codehub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.*;

@Entity
@Data
@Table(name = "perfiles_empleados")
public class PerfilEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El teléfono personal es obligatorio")
    @Pattern(regexp = "\\d{10}", message = "El teléfono personal debe tener 10 dígitos")
    private String telefonoPersonal;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 5, max = 200, message = "La dirección debe tener entre 5 y 200 caracteres")
    private String direccion;

    @NotNull(message = "La fecha de contratación es obligatoria")
    @Column(nullable = false)
    private Date fechaContratacion;

    @NotBlank(message = "La especialidad técnica es obligatoria")
    @Size(min = 2, max = 60, message = "La especialidad técnica debe tener entre 2 y 60 caracteres")
    @Column(nullable = false)
    private String especialidadTecnica;

    @Size(max = 200, message = "Las certificaciones no deben superar los 200 caracteres")
    private String certificaciones;

    @NotBlank(message = "La modalidad de trabajo es obligatoria")
    @Pattern(
            regexp = "PRESENCIAL|REMOTO|HIBRIDO",
            message = "La modalidad de trabajo debe ser PRESENCIAL, REMOTO o HIBRIDO"
    )
    @Column(nullable = false)
    private String modalidadTrabajo;

    @OneToOne
    @JoinColumn(name = "empleado_id", nullable = false, unique = true)
    @JsonIgnoreProperties("perfilEmpleado")
    private Empleado empleado;
}