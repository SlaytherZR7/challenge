package com.ntt.challenge.service;

import com.ntt.challenge.dto.ClienteRequestDTO;
import com.ntt.challenge.dto.ClienteResponseDTO;
import com.ntt.challenge.dto.ClienteUpdateDTO;
import com.ntt.challenge.exception.ClienteNoEncontradoException;
import com.ntt.challenge.model.Cliente;
import com.ntt.challenge.model.Genero;
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
        Cliente cliente = clienteMapper.toEntity(dto);

        // Valor por defecto para estado
        if (cliente.getEstado() == null) {
            cliente.setEstado(true);
        }

        // Valor por defecto para género
        if (cliente.getGenero() == null) {
            cliente.setGenero(Genero.NO_ESPECIFICADO);
        }

        Cliente guardado = clienteRepository.save(cliente);
        return clienteMapper.toDTO(guardado);
    }

    @Override
    public ClienteResponseDTO obtener(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente con ID " + id + " no encontrado"));
        return clienteMapper.toDTO(cliente);
    }

    @Override
    public List<ClienteResponseDTO> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toDTOList(clientes);
    }

    @Override
    public ClienteResponseDTO actualizar(UUID id, ClienteUpdateDTO dto) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente con ID " + id + " no encontrado"));

        clienteMapper.updateClienteFromDto(dto, clienteExistente);
        Cliente actualizado = clienteRepository.save(clienteExistente);
        return clienteMapper.toDTO(actualizado);
    }

    @Override
    public void eliminar(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente con ID " + id + " no encontrado"));

        clienteRepository.delete(cliente);
    }
}
