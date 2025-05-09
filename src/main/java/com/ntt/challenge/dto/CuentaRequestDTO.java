package com.ntt.challenge.dto;

import com.ntt.challenge.model.TipoCuenta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record CuentaRequestDTO(
        @NotBlank(message = "El número de cuenta no puede estar vacío")
        String numeroCuenta,
        @NotNull(message = "El tipo de cuenta no puede estar vacío")
        TipoCuenta tipoCuenta,
        @PositiveOrZero(message = "El saldo inicial no puede ser negativo")
        BigDecimal saldoInicial,
        Boolean estado,
        @NotNull
        UUID clienteId // ID del cliente asociado (heredado de la clase Persona)
) {}
