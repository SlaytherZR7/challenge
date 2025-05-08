package com.ntt.challenge.dto;

import com.ntt.challenge.model.TipoMovimiento;

import java.util.UUID;

public record MovimientoRequestDTO(
        UUID cuentaId,
        TipoMovimiento tipoMovimiento,
        Double valor
) {
}
