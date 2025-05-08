package com.ntt.challenge.dto;

import com.ntt.challenge.model.TipoCuenta;

public record CuentaUpdateDTO(
        TipoCuenta tipoCuenta,
        Boolean estado
) {
}
