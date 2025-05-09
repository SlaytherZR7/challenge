package com.ntt.challenge.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente extends Persona{
    @NotBlank(message = "El código de cliente no puede estar vacío")
    @Column(unique = true, nullable = false)
    private String codigoCliente; // ClienteId -> Documeto

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String contrasena;

    @NotNull
    @Column(nullable = false)
    private Boolean estado = true;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cuenta> cuentas = new ArrayList<>();
}
