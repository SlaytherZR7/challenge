package com.ntt.challenge.dto;

import com.ntt.challenge.model.TipoMovimiento;

import java.math.BigDecimal;
import java.util.UUID;

public record MovimientoRequestDTO(
        UUID cuentaId,
        TipoMovimiento tipoMovimiento,
        BigDecimal valor
) {
}
