package com.ntt.challenge.service;

import com.ntt.challenge.dto.CuentaRequestDTO;
import com.ntt.challenge.dto.CuentaResponseDTO;
import com.ntt.challenge.model.Cuenta;
import com.ntt.challenge.repository.CuentaRepository;
import com.ntt.challenge.utils.CuentaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, CuentaMapper cuentaMapper) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaMapper = cuentaMapper;
    }

    @Override
    public CuentaResponseDTO crear(CuentaRequestDTO cuentaRequestDTO) {
        Cuenta cuenta = cuentaMapper.cuentaRequestDTOToCuenta(cuentaRequestDTO);
        Cuenta guardada = cuentaRepository.save(cuenta);
        return cuentaMapper.cuentaToCuentaResponseDTO(guardada);
    }

    @Override
    public CuentaResponseDTO obtener(UUID id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return cuentaMapper.cuentaToCuentaResponseDTO(cuenta);
    }

    @Override
    public List<CuentaResponseDTO> listar() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        return cuentaMapper.cuentasToCuentaResponseDTOs(cuentas);
    }

    @Override
    public CuentaResponseDTO actualizar(UUID id, CuentaRequestDTO cuentaRequestDTO) {
        Cuenta existente = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        cuentaMapper.actualizarCuentaDesdeDTO(cuentaRequestDTO, existente);

        Cuenta actualizada = cuentaRepository.save(existente);
        return cuentaMapper.cuentaToCuentaResponseDTO(actualizada);
    }

    @Override
    public void eliminar(UUID id) {
        if (!cuentaRepository.existsById(id)) {
            throw new RuntimeException("Cuenta no encontrada");
        }
        cuentaRepository.deleteById(id);
    }
}
