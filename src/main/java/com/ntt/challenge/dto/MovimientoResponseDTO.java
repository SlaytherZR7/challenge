package com.ntt.challenge.dto;

import com.ntt.challenge.model.TipoCuenta;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovimientoResponseDTO(
        LocalDate fecha,
        String cliente,
        String numeroCuenta,
        TipoCuenta tipoCuenta,
        BigDecimal saldoInicial,
        Boolean estado,
        BigDecimal movimiento,
        BigDecimal saldoDisponible
) {
}
