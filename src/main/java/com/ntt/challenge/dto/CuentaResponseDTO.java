package com.ntt.challenge.dto;

import com.ntt.challenge.model.TipoCuenta;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "DTO de respuesta con los datos de una cuenta")
public record CuentaResponseDTO(
        @Schema(description = "ID único de la cuenta", example = "c5f3a7e0-d4a4-4f6e-b453-2d6c16b6f6f9")
        UUID id,
        @Schema(description = "Número de cuenta", example = "1234567890")
        String numeroCuenta,
        @Schema(description = "Tipo de cuenta", example = "CORRIENTE")
        TipoCuenta tipoCuenta,
        @Schema(description = "Saldo inicial", example = "500.00")
        BigDecimal saldoInicial,
        @Schema(description = "Estado actual de la cuenta", example = "true")
        Boolean estado,
//        @Schema(description = "ID del cliente asociado", example = "550e8400-e29b-41d4-a716-446655440000")
//        UUID clienteId, // ID del cliente asociado (heredado de la clase Persona)
        @Schema(description = "Nombre del cliente asociado", example = "Juan Pérez")
        String nombreCliente
) {}
