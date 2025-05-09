package com.ntt.challenge.utils;

import com.ntt.challenge.dto.MovimientoRequestDTO;
import com.ntt.challenge.dto.MovimientoResponseDTO;
import com.ntt.challenge.model.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    @Mapping(source = "cuenta.cliente.nombre", target = "cliente")
    @Mapping(source = "cuenta.numeroCuenta", target = "numeroCuenta")
    @Mapping(source = "cuenta.tipoCuenta", target = "tipoCuenta")
    @Mapping(source = "cuenta.saldoInicial", target = "saldoInicial")
    @Mapping(source = "cuenta.estado", target = "estado")
    @Mapping(source = "valor", target = "movimiento")
    @Mapping(source = "saldo", target = "saldoDisponible")
    MovimientoResponseDTO toDTO(Movimiento movimiento);

    Movimiento toEntity(MovimientoRequestDTO movimientoRequestDTO);

    List<MovimientoResponseDTO> toDTOList(List<Movimiento> movimientos);
}
