package com.ntt.challenge.utils;

import com.ntt.challenge.dto.MovimientoRequestDTO;
import com.ntt.challenge.dto.MovimientoResponseDTO;
import com.ntt.challenge.model.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    MovimientoResponseDTO movimientoToMovimientoResponseDTO(Movimiento movimiento);

    Movimiento movimientoRequestDTOToMovimiento(MovimientoRequestDTO movimientoRequestDTO);

    List<MovimientoResponseDTO> movimientoToMovimientoResponseDTOs(List<Movimiento> movimientos);

    void actualizarMovimientoDesdeDTO(MovimientoRequestDTO movimientoRequestDTO, @MappingTarget Movimiento movimiento);
}
