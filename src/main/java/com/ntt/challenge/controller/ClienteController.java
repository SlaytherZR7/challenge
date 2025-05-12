package com.ntt.challenge.controller;

import com.ntt.challenge.dto.ClienteRequestDTO;
import com.ntt.challenge.dto.ClienteResponseDTO;
import com.ntt.challenge.dto.ClienteUpdateDTO;
import com.ntt.challenge.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Operaciones relacionadas con la gestión de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Crear un nuevo cliente", description = "Registra un nuevo cliente con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente", content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<ClienteResponseDTO> crear(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO clienteResponseDTO = clienteService.crear(clienteRequestDTO);
        return ResponseEntity.ok(clienteResponseDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un cliente por ID", description = "Retorna los detalles de un cliente específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado", content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<ClienteResponseDTO> obtener(@Parameter(description = "ID del cliente") @PathVariable UUID id) {
        ClienteResponseDTO clienteResponseDTO = clienteService.obtener(id);
        return ResponseEntity.ok(clienteResponseDTO);
    }

    @GetMapping
    @Operation(summary = "Listar todos los clientes", description = "Retorna una lista de todos los clientes registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida", content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        List<ClienteResponseDTO> clientes = clienteService.listar();
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente", description = "Modifica los datos de un cliente existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado correctamente", content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<ClienteResponseDTO> actualizar(@Parameter(description = "ID del cliente") @PathVariable UUID id,
                                                          @Valid @RequestBody ClienteUpdateDTO clienteUpdateDTO) {
        ClienteResponseDTO clienteResponseDTO = clienteService.actualizar(id, clienteUpdateDTO);
        return ResponseEntity.ok(clienteResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente del sistema por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado correctamente"),
    })
    public ResponseEntity<Void> eliminar(@Parameter(description = "ID del cliente") @PathVariable UUID id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
