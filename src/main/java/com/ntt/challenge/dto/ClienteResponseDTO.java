package com.ntt.challenge.dto;

import com.ntt.challenge.model.Genero;

import java.util.UUID;

public record ClienteResponseDTO(
        UUID id,
        String codigoCliente, // ClienteId -> Documento
        String nombre,
        Genero genero,
        Integer edad,
        String dni,
        String direccion,
        String telefono,
        Boolean estado
) {
}
