package com.ntt.challenge.service;

import com.ntt.challenge.dto.ClienteRequestDTO;
import com.ntt.challenge.dto.ClienteResponseDTO;
import com.ntt.challenge.dto.ClienteUpdateDTO;
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
    public ClienteResponseDTO actualizar(UUID id, ClienteUpdateDTO dto) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        if (dto.contrasena() != null) clienteExistente.setContrasena(dto.contrasena());
        if (dto.nombre() != null) clienteExistente.setNombre(dto.nombre());
        if (dto.genero() != null) clienteExistente.setGenero(dto.genero());
        if (dto.edad() != null) clienteExistente.setEdad(dto.edad());
        if (dto.estado() != null) clienteExistente.setEstado(dto.estado());
        if (dto.direccion() != null) clienteExistente.setDireccion(dto.direccion());
        if (dto.telefono() != null) clienteExistente.setTelefono(dto.telefono());

        Cliente actualizado = clienteRepository.save(clienteExistente);
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
