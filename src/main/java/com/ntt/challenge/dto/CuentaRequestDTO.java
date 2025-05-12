package com.ntt.challenge.dto;

import com.ntt.challenge.model.TipoCuenta;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "DTO para crear una cuenta")
public record CuentaRequestDTO(
        @Schema(description = "Número de cuenta único", example = "1234567890")
        @NotBlank(message = "El número de cuenta no puede estar vacío")
        String numeroCuenta,
        @Schema(description = "Tipo de cuenta (AHORROS o CORRIENTE)", example = "AHORROS")
        @NotNull(message = "El tipo de cuenta no puede estar vacío")
        TipoCuenta tipoCuenta,
        @Schema(description = "Saldo inicial de la cuenta", example = "1000.00")
        @PositiveOrZero(message = "El saldo inicial no puede ser negativo")
        BigDecimal saldoInicial,
        @Schema(description = "Estado de la cuenta (true por defecto si no se especifica)", example = "true", defaultValue = "true")
        Boolean estado,
        @Schema(description = "ID del cliente asociado", example = "550e8400-e29b-41d4-a716-446655440000")
        @NotNull
        UUID clienteId // ID del cliente asociado (heredado de la clase Persona)
) {}
