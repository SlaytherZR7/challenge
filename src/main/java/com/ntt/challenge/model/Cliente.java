package com.ntt.challenge.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente extends Persona{
    @Column(unique = true, nullable = false)
    private String clienteId;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String contrasena;

    @NotNull
    @Column(nullable = false)
    private Boolean estado;
}
