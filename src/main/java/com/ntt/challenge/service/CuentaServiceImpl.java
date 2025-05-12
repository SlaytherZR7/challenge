package com.ntt.challenge.service;

import com.ntt.challenge.dto.CuentaRequestDTO;
import com.ntt.challenge.dto.CuentaResponseDTO;
import com.ntt.challenge.dto.CuentaUpdateDTO;
import com.ntt.challenge.exception.ClienteNoEncontradoException;
import com.ntt.challenge.exception.CuentaNoEncontradaException;
import com.ntt.challenge.model.Cliente;
import com.ntt.challenge.model.Cuenta;
import com.ntt.challenge.repository.ClienteRepository;
import com.ntt.challenge.repository.CuentaRepository;
import com.ntt.challenge.utils.CuentaMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;
    private final CuentaMapper cuentaMapper;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, CuentaMapper cuentaMapper, ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaMapper = cuentaMapper;
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public CuentaResponseDTO crear(CuentaRequestDTO cuentaRequestDTO) {
        Cuenta cuenta = cuentaMapper.toEntity(cuentaRequestDTO);

        if (cuenta.getEstado() == null) {
            cuenta.setEstado(true);
        }
        if (cuenta.getSaldoInicial() == null) {
            cuenta.setSaldoInicial(BigDecimal.ZERO);
        }

        Cliente cliente = clienteRepository.findById(cuentaRequestDTO.clienteId())
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente con ID " + cuentaRequestDTO.clienteId() + " no encontrado"));

        cuenta.setCliente(cliente);

        Cuenta guardada = cuentaRepository.save(cuenta);
        return cuentaMapper.toDTO(guardada);
    }

    @Override
    public CuentaResponseDTO obtener(UUID id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta con ID " + id + " no encontrada"));
        return cuentaMapper.toDTO(cuenta);
    }

    @Override
    public List<CuentaResponseDTO> listar() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        return cuentaMapper.toDTOList(cuentas);
    }

    @Override
    @Transactional
    public CuentaResponseDTO actualizar(UUID id, CuentaUpdateDTO cuentaUpdateDTO) {
        Cuenta cuentaExistente = cuentaRepository.findById(id)
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta con ID " + id + " no encontrada"));

        cuentaMapper.updateCuentaFromDto(cuentaUpdateDTO, cuentaExistente);

        Cuenta actualizada = cuentaRepository.save(cuentaExistente);
        return cuentaMapper.toDTO(actualizada);
    }

    @Override
    @Transactional
    public void eliminar(UUID id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta con ID " + id + " no encontrada"));

        cuentaRepository.delete(cuenta);
    }
}
