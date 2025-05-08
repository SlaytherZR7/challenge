package com.ntt.challenge.utils;

import com.ntt.challenge.dto.ClienteRequestDTO;
import com.ntt.challenge.dto.ClienteResponseDTO;
import com.ntt.challenge.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponseDTO toDTO(Cliente cliente);

    Cliente toEntity(ClienteRequestDTO dto);

    List<ClienteResponseDTO> toDTOList(List<Cliente> clientes);
}
