package com.ntt.challenge.utils;

import com.ntt.challenge.dto.CuentaRequestDTO;
import com.ntt.challenge.dto.CuentaResponseDTO;
import com.ntt.challenge.model.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.nombre", target = "nombreCliente")
    CuentaResponseDTO toDTO(Cuenta cuenta);

    Cuenta toEntity(CuentaRequestDTO cuentaRequestDTO);

    List<CuentaResponseDTO> toDTOList(List<Cuenta> cuentas);

    void actualizarCuentaDesdeDTO(CuentaRequestDTO cuentaRequestDTO, @MappingTarget Cuenta cuenta);
}
