package com.ntt.challenge.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "DTO para crear un movimiento")
public record MovimientoRequestDTO(
        @Schema(description = "ID de la cuenta asociada al movimiento", example = "550e8400-e29b-41d4-a716-446655440000")
        @NotNull(message = "La cuenta es obligatoria")
        UUID cuentaId,
        @Schema(description = "Valor del movimiento. Positivo para depósito, negativo para retiro.", example = "100.00")
        @NotNull(message = "El valor del movimiento es obligatorio")
        BigDecimal valor,

        @Schema(description = "Fecha del movimiento (opcional, se usa la fecha actual si no se especifica)", example = "2025-05-12")
        LocalDate fecha
) {
}
