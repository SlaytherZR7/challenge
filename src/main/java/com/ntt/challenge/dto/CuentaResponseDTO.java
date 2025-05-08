package com.ntt.challenge.dto;

import com.ntt.challenge.model.TipoCuenta;

import java.math.BigDecimal;
import java.util.UUID;

public record CuentaResponseDTO(
        UUID id,
        String numeroCuenta,
        TipoCuenta tipoCuenta,
        BigDecimal saldoInicial,
        Boolean estado,
        UUID clienteId, // ID del cliente asociado (heredado de la clase Persona)
        String nombreCliente
) {}
