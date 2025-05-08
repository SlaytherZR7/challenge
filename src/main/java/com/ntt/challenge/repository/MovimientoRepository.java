package com.ntt.challenge.repository;

import com.ntt.challenge.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovimientoRepository extends JpaRepository<Movimiento, UUID> {
}
