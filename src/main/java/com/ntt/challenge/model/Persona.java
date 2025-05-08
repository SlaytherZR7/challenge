package com.ntt.challenge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El DNI no puede estar vacío")
    @Column(unique = true, nullable = false)
    private String dni;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Positive(message = "La edad debe ser un número positivo")
    @Column(nullable = false)
    private Integer edad;

    private String telefono;

    private String direccion;
}
