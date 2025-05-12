package com.ntt.challenge.utils;

import com.ntt.challenge.dto.CuentaRequestDTO;
import com.ntt.challenge.dto.CuentaResponseDTO;
import com.ntt.challenge.dto.CuentaUpdateDTO;
import com.ntt.challenge.model.Cuenta;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    @Mapping(source = "cliente.nombre", target = "nombreCliente")
    CuentaResponseDTO toDTO(Cuenta cuenta);

    Cuenta toEntity(CuentaRequestDTO cuentaRequestDTO);

    List<CuentaResponseDTO> toDTOList(List<Cuenta> cuentas);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCuentaFromDto(CuentaUpdateDTO dto, @MappingTarget Cuenta cuenta);
}
