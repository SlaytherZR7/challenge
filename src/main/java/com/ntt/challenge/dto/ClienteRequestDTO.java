package com.ntt.challenge.dto;

import com.ntt.challenge.model.Genero;
import jakarta.validation.constraints.PositiveOrZero;

public record ClienteRequestDTO(
        String clienteId,
        String contrasena,
        Boolean estado,
        String nombre,
        Genero genero,

        @PositiveOrZero(message = "La edad no puede ser negativa")
        Integer edad,

        String identificacion,
        String direccion,
        String telefono
) {
}
