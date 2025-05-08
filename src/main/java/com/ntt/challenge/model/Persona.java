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

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String dni;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Positive
    @Column(nullable = false)
    private Integer edad;

    private String telefono;

    private String direccion;
}
