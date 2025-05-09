package com.ntt.challenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ntt.challenge.model.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ClienteRequestDTO(
        @NotBlank(message = "El código de cliente no puede estar vacío")
        String codigoCliente,// ClienteId -> Documento
        @NotBlank(message = "La contraseña no puede estar vacía")
        String contrasena,
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        Genero genero,
        @NotBlank(message = "El DNI no puede estar vacío")
        String dni,

        @PositiveOrZero(message = "La edad no puede ser negativa")
        Integer edad,

        //Opcional
        Boolean estado,
        String direccion,
        String telefono
) {
}
