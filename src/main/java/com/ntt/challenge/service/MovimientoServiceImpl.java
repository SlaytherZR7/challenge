package com.ntt.challenge.service;

import com.ntt.challenge.dto.MovimientoRequestDTO;
import com.ntt.challenge.dto.MovimientoResponseDTO;
import com.ntt.challenge.exception.CuentaNoEncontradaException;
import com.ntt.challenge.exception.MovimientoInvalidoException;
import com.ntt.challenge.exception.MovimientoNoEncontradoException;
import com.ntt.challenge.exception.SaldoNoDisponibleException;
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
        Cuenta cuenta = obtenerCuenta(movimientoRequestDTO.cuentaId());

        TipoMovimiento tipo = determinarTipoMovimiento(movimientoRequestDTO.valor());
        validarMovimiento(movimientoRequestDTO.valor(), cuenta.getSaldoInicial());

        BigDecimal saldoAnterior = cuenta.getSaldoInicial();
        BigDecimal nuevoSaldo = saldoAnterior.add(movimientoRequestDTO.valor());

        Movimiento movimiento = construirMovimiento(movimientoRequestDTO, cuenta, tipo, nuevoSaldo);
        movimientoRepository.save(movimiento);

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        return construirResponseDTO(movimiento, cuenta, saldoAnterior);
    }

    @Override
    public MovimientoResponseDTO obtener(UUID id) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new MovimientoNoEncontradoException("Movimiento con ID " + id + " no encontrado"));
        return movimientoMapper.toDTO(movimiento);
    }

    @Override
    public List<MovimientoResponseDTO> listar() {
        List<Movimiento> movimientos = movimientoRepository.findAll();
        return movimientoMapper.toDTOList(movimientos);
    }

    @Override
    @Transactional
    public MovimientoResponseDTO actualizar(UUID id, MovimientoRequestDTO movimientoRequestDTO) {
        throw new UnsupportedOperationException("Actualizar movimientos no está permitido por reglas de negocio");
    }

    @Override
    @Transactional
    public void eliminar(UUID id) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new MovimientoNoEncontradoException("Movimiento con ID " + id + " no encontrado"));
        movimientoRepository.delete(movimiento);
    }

    private Cuenta obtenerCuenta(UUID cuentaId) {
        return cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta con ID " + cuentaId + " no encontrada"));
    }

    private TipoMovimiento determinarTipoMovimiento(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) > 0) return TipoMovimiento.DEPOSITO;
        if (valor.compareTo(BigDecimal.ZERO) < 0) return TipoMovimiento.RETIRO;
        throw new MovimientoInvalidoException("No se puede realizar un movimiento con valor cero");
    }

    private void validarMovimiento(BigDecimal valor, BigDecimal saldoActual) {
        BigDecimal nuevoSaldo = saldoActual.add(valor);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoNoDisponibleException("Saldo no disponible para realizar el movimiento");
        }
    }

    private Movimiento construirMovimiento(MovimientoRequestDTO dto, Cuenta cuenta, TipoMovimiento tipo, BigDecimal nuevoSaldo) {
        Movimiento movimiento = movimientoMapper.toEntity(dto);
        movimiento.setCuenta(cuenta);
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setValor(dto.valor());
        movimiento.setTipoMovimiento(tipo);
        movimiento.setFecha(dto.fecha() != null ? dto.fecha() : LocalDate.now());
        return movimiento;
    }

    private MovimientoResponseDTO construirResponseDTO(Movimiento movimiento, Cuenta cuenta, BigDecimal saldoAnterior) {
        MovimientoResponseDTO dto = new MovimientoResponseDTO();
        dto.setFecha(movimiento.getFecha());
        dto.setCliente(cuenta.getCliente().getNombre());
        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
        dto.setTipoCuenta(cuenta.getTipoCuenta());
        dto.setSaldoInicial(saldoAnterior);
        dto.setEstado(cuenta.getEstado());
        dto.setMovimiento(movimiento.getValor());
        dto.setSaldoDisponible(movimiento.getSaldo());
        return dto;
    }
}
