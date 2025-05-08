package com.ntt.challenge.repository;


import com.ntt.challenge.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CuentaRepository extends JpaRepository<Cuenta, UUID> {
}
