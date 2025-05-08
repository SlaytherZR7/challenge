package com.ntt.challenge.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record MovimientoRequestDTO(
        @NotNull(message = "La cuenta es obligatoria")
        UUID cuentaId,
        @NotNull(message = "El valor del movimiento es obligatorio")
        BigDecimal valor,

        LocalDate fecha
) {
}
