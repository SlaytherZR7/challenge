package com.ntt.challenge.dto;

import com.ntt.challenge.model.TipoCuenta;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para actualizar una cuenta")
public record CuentaUpdateDTO(
        @Schema(description = "Nuevo tipo de cuenta", example = "AHORROS")
        TipoCuenta tipoCuenta,
        @Schema(description = "Nuevo estado de la cuenta", example = "false")
        Boolean estado
) {
}
