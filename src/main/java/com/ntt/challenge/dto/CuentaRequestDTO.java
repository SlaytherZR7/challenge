package com.ntt.challenge.dto;

import com.ntt.challenge.model.TipoCuenta;

import java.util.UUID;

public record CuentaRequestDTO(
        String numeroCuenta,
        TipoCuenta tipoCuenta,
        Double saldoInicial,
        Boolean estado,
        UUID clienteId
) {}
