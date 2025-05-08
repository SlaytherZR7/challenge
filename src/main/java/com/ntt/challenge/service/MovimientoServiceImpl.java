package com.ntt.challenge.service;

import com.ntt.challenge.dto.MovimientoRequestDTO;
import com.ntt.challenge.dto.MovimientoResponseDTO;
import com.ntt.challenge.model.Cuenta;
import com.ntt.challenge.model.Movimiento;
import com.ntt.challenge.model.TipoMovimiento;
import com.ntt.challenge.repository.CuentaRepository;
import com.ntt.challenge.repository.MovimientoRepository;
import com.ntt.challenge.utils.MovimientoMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    private final MovimientoMapper movimientoMapper;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository, MovimientoMapper movimientoMapper) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
        this.movimientoMapper = movimientoMapper;
    }

    @Override
    @Transactional
    public MovimientoResponseDTO crear(MovimientoRequestDTO movimientoRequestDTO) {
        Cuenta cuenta = cuentaRepository.findById(movimientoRequestDTO.cuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        BigDecimal valor = movimientoRequestDTO.valor();
        TipoMovimiento tipoMovimiento;

        if (valor.compareTo(BigDecimal.ZERO) > 0) {
            tipoMovimiento = TipoMovimiento.DEPOSITO;
        } else if (valor.compareTo(BigDecimal.ZERO) < 0) {
            tipoMovimiento = TipoMovimiento.RETIRO;
        } else {
            throw new RuntimeException("El valor del movimiento no puede ser cero");
        }

        BigDecimal nuevoSaldo = cuenta.getSaldoInicial().add(valor);

        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Saldo no disponible");
        }

        LocalDate fechaMovimiento = movimientoRequestDTO.fecha() != null ? movimientoRequestDTO.fecha() : LocalDate.now();

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        Movimiento movimiento = movimientoMapper.toEntity(movimientoRequestDTO);
        movimiento.setCuenta(cuenta);
        movimiento.setSaldo(nuevoSaldo);

        Movimiento guardado = movimientoRepository.save(movimiento);
        return movimientoMapper.toDTO(guardado);
    }

    @Override
    public MovimientoResponseDTO obtener(UUID id) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        return movimientoMapper.toDTO(movimiento);
    }

    @Override
    public List<MovimientoResponseDTO> listar() {
        List<Movimiento> movimientos = movimientoRepository.findAll();
        return movimientoMapper.toDTOList(movimientos);
    }

    @Override
    public MovimientoResponseDTO actualizar(UUID id, MovimientoRequestDTO movimientoRequestDTO) {
        throw new UnsupportedOperationException("Actualizar movimientos no está permitido por reglas de negocio");
    }

    @Override
    public void eliminar(UUID id) {
        if (!movimientoRepository.existsById(id)) {
            throw new RuntimeException("Movimiento no encontrado");
        }
        movimientoRepository.deleteById(id);
    }
}
