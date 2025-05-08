package com.ntt.challenge.dto;

import java.time.LocalDate;

public record MovimientoResponseDTO(
        LocalDate fecha,
        String cliente,
        String numeroCuenta,
        String tipoCuenta,
        Double saldoInicial,
        Boolean estado,
        Double movimiento,
        Double saldoDisponible
) {
}
