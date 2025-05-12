package com.ntt.challenge.utils;

import com.ntt.challenge.dto.ClienteRequestDTO;
import com.ntt.challenge.dto.ClienteResponseDTO;
import com.ntt.challenge.dto.ClienteUpdateDTO;
import com.ntt.challenge.model.Cliente;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponseDTO toDTO(Cliente cliente);

    Cliente toEntity(ClienteRequestDTO dto);

    List<ClienteResponseDTO> toDTOList(List<Cliente> clientes);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClienteFromDto(ClienteUpdateDTO dto, @MappingTarget Cliente cliente);
}
