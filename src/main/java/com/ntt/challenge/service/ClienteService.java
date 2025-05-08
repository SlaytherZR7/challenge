package com.ntt.challenge.service;

import com.ntt.challenge.dto.ClienteRequestDTO;
import com.ntt.challenge.dto.ClienteResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
    ClienteResponseDTO crear(ClienteRequestDTO dto);

    ClienteResponseDTO obtener(UUID id);

    List<ClienteResponseDTO> listar();

    ClienteResponseDTO actualizar(UUID id, ClienteRequestDTO dto);

    void eliminar(UUID id);
}
