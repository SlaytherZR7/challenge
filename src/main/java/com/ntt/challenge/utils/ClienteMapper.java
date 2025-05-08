package com.ntt.challenge.utils;

import com.ntt.challenge.dto.ClienteRequestDTO;
import com.ntt.challenge.dto.ClienteResponseDTO;
import com.ntt.challenge.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponseDTO clienteToClienteResponseDTO(Cliente cliente);

    Cliente clienteRequestDTOToCliente(ClienteRequestDTO dto);

    List<ClienteResponseDTO> clientesToClienteResponseDTOs(List<Cliente> clientes);

    void actualizarClienteDesdeDTO(ClienteRequestDTO dto, @MappingTarget Cliente cliente);
}
