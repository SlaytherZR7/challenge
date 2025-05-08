package com.ntt.challenge.controller;

import com.ntt.challenge.dto.ClienteRequestDTO;
import com.ntt.challenge.dto.ClienteResponseDTO;
import com.ntt.challenge.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crear(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO clienteResponseDTO = clienteService.crear(clienteRequestDTO);
        return ResponseEntity.ok(clienteResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> obtener(@PathVariable UUID id) {
        ClienteResponseDTO clienteResponseDTO = clienteService.obtener(id);
        return ResponseEntity.ok(clienteResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        List<ClienteResponseDTO> clientes = clienteService.listar();
        return ResponseEntity.ok(clientes);
    }

    @PutMapping
    public ResponseEntity<ClienteResponseDTO> actualizar(@PathVariable UUID id,
                                                          @Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO clienteResponseDTO = clienteService.actualizar(id, clienteRequestDTO);
        return ResponseEntity.ok(clienteResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
