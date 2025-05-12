package com.ntt.challenge.controller;

import com.ntt.challenge.dto.MovimientoRequestDTO;
import com.ntt.challenge.dto.MovimientoResponseDTO;
import com.ntt.challenge.service.MovimientoService;
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
@RequestMapping("/api/movimientos")
@Tag(name = "Movimientos", description = "Operaciones relacionadas con los movimientos bancarios")
public class MovimientoController {

    private final MovimientoService movimientoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Registrar un movimiento", description = "Crea un nuevo movimiento (depósito o retiro) en una cuenta existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movimiento registrado exitosamente", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<MovimientoResponseDTO> crear(@Valid @RequestBody MovimientoRequestDTO movimientoRequestDTO) {
        MovimientoResponseDTO movimientoResponseDTO = movimientoService.crear(movimientoRequestDTO);
        return ResponseEntity.ok(movimientoResponseDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un movimiento por ID", description = "Retorna los detalles de un movimiento específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento encontrado", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<MovimientoResponseDTO> obtener(@Parameter(description = "ID del movimiento") @PathVariable UUID id) {
        MovimientoResponseDTO movimientoResponseDTO = movimientoService.obtener(id);
        return ResponseEntity.ok(movimientoResponseDTO);
    }

    @GetMapping
    @Operation(summary = "Listar movimientos", description = "Retorna todos los movimientos realizados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de movimientos obtenida", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<MovimientoResponseDTO>> listar() {
        List<MovimientoResponseDTO> movimientos = movimientoService.listar();
        return ResponseEntity.ok(movimientos);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un movimiento", description = "Modifica un movimiento existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento actualizado correctamente", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<MovimientoResponseDTO> actualizar(@Parameter(description = "ID del movimiento") @PathVariable UUID id,
                                                             @Valid @RequestBody MovimientoRequestDTO movimientoRequestDTO) {
        MovimientoResponseDTO movimientoResponseDTO = movimientoService.actualizar(id, movimientoRequestDTO);
        return ResponseEntity.ok(movimientoResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un movimiento", description = "Elimina un movimiento del sistema por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movimiento eliminado correctamente")
    })
    public ResponseEntity<Void> eliminar(@Parameter(description = "ID del movimiento") @PathVariable UUID id) {
        movimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
