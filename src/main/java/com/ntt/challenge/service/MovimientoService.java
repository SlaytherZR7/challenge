package com.ntt.challenge.service;

import com.ntt.challenge.dto.MovimientoRequestDTO;
import com.ntt.challenge.dto.MovimientoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface MovimientoService {
    MovimientoResponseDTO crear(MovimientoRequestDTO movimientoRequestDTO);

    MovimientoResponseDTO obtener(UUID id);

    List<MovimientoResponseDTO> listar();

    MovimientoResponseDTO actualizar(UUID id, MovimientoRequestDTO movimientoRequestDTO);

    void eliminar(UUID id);
}
