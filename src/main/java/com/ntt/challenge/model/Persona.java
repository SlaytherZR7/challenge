package com.ntt.challenge.model;

import jakarta.persistence.*;
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

    @Not
    private String nombre;
    private String dni;
    //Enum
    private String genero;

    private Integer edad;

    private String telefono;

    private String direccion;
}
