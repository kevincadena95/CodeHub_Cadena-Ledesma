package com.itsqmet.codehub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name="usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El usename es obligatorio")
    @Column(unique=true, nullable = false)
    private String username;

    @NotBlank(message = "El password es obligatoria")
    @Column( nullable = false)
    private String password;

    @NotBlank(message = "El email es obligatorio")
    @Column(unique=true, nullable = false)
    private String email;

    @NotBlank(message = "El rol es obligatorio")
    @Column(nullable = false)
    private String rol;

}
