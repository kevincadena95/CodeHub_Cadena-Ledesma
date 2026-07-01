package com.itsqmet.codehub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@Entity
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
    @JoinColumn(name = "empleado_id", nullable = false)
    @JsonIgnoreProperties("perfilEmpleado")
    private Empleado empleado;

    public PerfilEmpleado() {
    }

    public PerfilEmpleado(Long id, String telefonoPersonal, String direccion, Date fechaContratacion, String especialidadTecnica, String certificaciones, String modalidadTrabajo, Empleado empleado) {
        this.id = id;
        this.telefonoPersonal = telefonoPersonal;
        this.direccion = direccion;
        this.fechaContratacion = fechaContratacion;
        this.especialidadTecnica = especialidadTecnica;
        this.certificaciones = certificaciones;
        this.modalidadTrabajo = modalidadTrabajo;
        this.empleado = empleado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefonoPersonal() {
        return telefonoPersonal;
    }

    public void setTelefonoPersonal(String telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getEspecialidadTecnica() {
        return especialidadTecnica;
    }

    public void setEspecialidadTecnica(String especialidadTecnica) {
        this.especialidadTecnica = especialidadTecnica;
    }

    public String getCertificaciones() {
        return certificaciones;
    }

    public void setCertificaciones(String certificaciones) {
        this.certificaciones = certificaciones;
    }

    public String getModalidadTrabajo() {
        return modalidadTrabajo;
    }

    public void setModalidadTrabajo(String modalidadTrabajo) {
        this.modalidadTrabajo = modalidadTrabajo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}