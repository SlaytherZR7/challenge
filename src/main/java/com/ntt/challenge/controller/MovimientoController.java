package com.ntt.challenge.controller;

import com.ntt.challenge.dto.MovimientoRequestDTO;
import com.ntt.challenge.dto.MovimientoResponseDTO;
import com.ntt.challenge.service.MovimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<MovimientoResponseDTO> crear(@Valid @RequestBody MovimientoRequestDTO movimientoRequestDTO) {
        MovimientoResponseDTO movimientoResponseDTO = movimientoService.crear(movimientoRequestDTO);
        return ResponseEntity.ok(movimientoResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoResponseDTO> obtener(@PathVariable UUID id) {
        MovimientoResponseDTO movimientoResponseDTO = movimientoService.obtener(id);
        return ResponseEntity.ok(movimientoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<MovimientoResponseDTO>> listar() {
        List<MovimientoResponseDTO> movimientos = movimientoService.listar();
        return ResponseEntity.ok(movimientos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoResponseDTO> actualizar(@PathVariable UUID id,
                                                             @Valid @RequestBody MovimientoRequestDTO movimientoRequestDTO) {
        MovimientoResponseDTO movimientoResponseDTO = movimientoService.actualizar(id, movimientoRequestDTO);
        return ResponseEntity.ok(movimientoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        movimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
