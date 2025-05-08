package com.ntt.challenge.dto;

import com.ntt.challenge.model.Genero;

public record ClienteUpdateDTO(
        String contrasena,
        String nombre,
        Genero genero,
        Integer edad,
        Boolean estado,
        String direccion,
        String telefono
) {
}
