package com.ntt.challenge.controller;

import com.ntt.challenge.dto.CuentaRequestDTO;
import com.ntt.challenge.dto.CuentaResponseDTO;
import com.ntt.challenge.dto.CuentaUpdateDTO;
import com.ntt.challenge.service.CuentaService;
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
@RequestMapping("/api/cuentas")
@Tag(name = "Cuentas", description = "Operaciones relacionadas con la gestión de cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cuenta creada exitosamente", content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<CuentaResponseDTO> crear(@Valid @RequestBody CuentaRequestDTO cuentaRequestDTO) {
        CuentaResponseDTO cuentaResponseDTO = cuentaService.crear(cuentaRequestDTO);
        return ResponseEntity.ok(cuentaResponseDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una cuenta por ID", description = "Retorna los detalles de una cuenta específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta encontrada", content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<CuentaResponseDTO> obtener(@Parameter(description = "ID de la cuenta") @PathVariable UUID id) {
        CuentaResponseDTO cuentaResponseDTO = cuentaService.obtener(id);
        return ResponseEntity.ok(cuentaResponseDTO);
    }

    @GetMapping
    @Operation(summary = "Listar todas las cuentas", description = "Retorna una lista de todas las cuentas registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cuentas obtenida", content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<List<CuentaResponseDTO>> listar() {
        List<CuentaResponseDTO> cuentas = cuentaService.listar();
        return ResponseEntity.ok(cuentas);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una cuenta", description = "Modifica los datos de una cuenta existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta actualizada correctamente", content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<CuentaResponseDTO> actualizar(@PathVariable UUID id,
                                                         @Valid @RequestBody CuentaUpdateDTO cuentaUpdateDTO) {
        CuentaResponseDTO cuentaResponseDTO = cuentaService.actualizar(id, cuentaUpdateDTO);
        return ResponseEntity.ok(cuentaResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una cuenta", description = "Elimina una cuenta del sistema por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cuenta eliminada correctamente"),
    })
    public ResponseEntity<Void> eliminar(@Parameter(description = "ID de la cuenta") @PathVariable UUID id) {
        cuentaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
