package com.ntt.challenge.dto;

import com.ntt.challenge.model.Genero;

import java.util.UUID;

public record ClienteResponseDTO(
        UUID id,
        String clienteId,
        String nombre,
        Genero genero,
        Integer edad,
        String identificacion,
        String direccion,
        String telefono,
        Boolean estado
) {
}
