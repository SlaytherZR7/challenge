package com.ntt.challenge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String numeroCuenta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCuenta tipoCuenta;

    @PositiveOrZero(message = "El saldo inicial no puede ser negativo")
    @Column(nullable = false)
    private BigDecimal saldoInicial = BigDecimal.ZERO;

    @Column(nullable = false)
    private Boolean estado = true;

    @ManyToOne(optional = false)
    private Cliente cliente;
}
