package com.ntt.challenge.service;

import com.ntt.challenge.dto.ClienteRequestDTO;
import com.ntt.challenge.dto.ClienteResponseDTO;
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
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return clienteMapper.toDTO(cliente);
    }

    @Override
    public List<ClienteResponseDTO> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toDTOList(clientes);
    }

    @Override
    public ClienteResponseDTO actualizar(UUID id, ClienteRequestDTO dto) {
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        clienteMapper.actualizarClienteDesdeDTO(dto, existente);

        Cliente actualizado = clienteRepository.save(existente);
        return clienteMapper.toDTO(actualizado);
    }

    @Override
    public void eliminar(UUID id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado");
        }
        clienteRepository.deleteById(id);
    }
}
