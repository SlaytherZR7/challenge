package com.ntt.challenge.dto;

import com.ntt.challenge.model.TipoCuenta;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Schema(description = "DTO de respuesta con los detalles de un movimiento")
public class MovimientoResponseDTO {
    @Schema(description = "Fecha del movimiento", example = "2025-05-12")
    private LocalDate fecha;
    @Schema(description = "Nombre del cliente asociado", example = "Juan Pérez")
    private String cliente;
    @Schema(description = "Número de cuenta", example = "1234567890")
    private String numeroCuenta;
    @Schema(description = "Tipo de cuenta", example = "AHORROS")
    private TipoCuenta tipoCuenta;
    @Schema(description = "Saldo inicial antes del movimiento", example = "1000.00")
    private BigDecimal saldoInicial;
    @Schema(description = "Estado de la cuenta", example = "true")
    private Boolean estado;
    @Schema(description = "Valor del movimiento (positivo para depósito, negativo para retiro)", example = "-200.00")
    private BigDecimal movimiento;
    @Schema(description = "Saldo disponible después del movimiento", example = "800.00")
    private BigDecimal saldoDisponible;
}
