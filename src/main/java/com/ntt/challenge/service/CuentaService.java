package com.ntt.challenge.service;

import com.ntt.challenge.dto.CuentaRequestDTO;
import com.ntt.challenge.dto.CuentaResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CuentaService {
    CuentaResponseDTO crear(CuentaRequestDTO cuentaRequestDTO);

    CuentaResponseDTO obtener(UUID id);

    List<CuentaResponseDTO> listar();

    CuentaResponseDTO actualizar(UUID id, CuentaRequestDTO cuentaRequestDTO);

    void eliminar(UUID id);
}
