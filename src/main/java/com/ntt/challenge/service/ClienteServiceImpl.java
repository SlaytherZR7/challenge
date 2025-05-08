package com.ntt.challenge.service;

import com.ntt.challenge.dto.ClienteRequestDTO;
import com.ntt.challenge.dto.ClienteResponseDTO;
import com.ntt.challenge.model.Cliente;
import com.ntt.challenge.repository.ClienteRepository;
import com.ntt.challenge.utils.ClienteMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteResponseDTO crear(ClienteRequestDTO dto) {
        Cliente cliente = clienteMapper.clienteRequestDTOToCliente(dto);
        Cliente guardado = clienteRepository.save(cliente);
        return clienteMapper.clienteToClienteResponseDTO(guardado);
    }

    @Override
    public ClienteResponseDTO obtener(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return clienteMapper.clienteToClienteResponseDTO(cliente);
    }

    @Override
    public List<ClienteResponseDTO> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.clientesToClienteResponseDTOs(clientes);
    }

    @Override
    public ClienteResponseDTO actualizar(UUID id, ClienteRequestDTO dto) {
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        clienteMapper.actualizarClienteDesdeDTO(dto, existente);

        Cliente actualizado = clienteRepository.save(existente);
        return clienteMapper.clienteToClienteResponseDTO(actualizado);
    }

    @Override
    public void eliminar(UUID id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado");
        }
        clienteRepository.deleteById(id);
    }
}
