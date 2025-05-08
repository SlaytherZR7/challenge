package com.ntt.challenge.utils;

import com.ntt.challenge.dto.CuentaRequestDTO;
import com.ntt.challenge.dto.CuentaResponseDTO;
import com.ntt.challenge.model.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    CuentaResponseDTO cuentaToCuentaResponseDTO(Cuenta cuenta);

    Cuenta cuentaRequestDTOToCuenta(CuentaRequestDTO cuentaRequestDTO);

    List<CuentaResponseDTO> cuentasToCuentaResponseDTOs(List<Cuenta> cuentas);

    void actualizarCuentaDesdeDTO(CuentaRequestDTO cuentaRequestDTO, @MappingTarget Cuenta cuenta);
}
