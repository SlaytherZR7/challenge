package com.ntt.challenge.controller;

import com.ntt.challenge.dto.CuentaRequestDTO;
import com.ntt.challenge.dto.CuentaResponseDTO;
import com.ntt.challenge.service.CuentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<CuentaResponseDTO> crear(@Valid @RequestBody CuentaRequestDTO cuentaRequestDTO) {
        CuentaResponseDTO cuentaResponseDTO = cuentaService.crear(cuentaRequestDTO);
        return ResponseEntity.ok(cuentaResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaResponseDTO> obtener(@PathVariable UUID id) {
        CuentaResponseDTO cuentaResponseDTO = cuentaService.obtener(id);
        return ResponseEntity.ok(cuentaResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CuentaResponseDTO>> listar() {
        List<CuentaResponseDTO> cuentas = cuentaService.listar();
        return ResponseEntity.ok(cuentas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaResponseDTO> actualizar(@PathVariable UUID id,
                                                         @Valid @RequestBody CuentaRequestDTO cuentaRequestDTO) {
        CuentaResponseDTO cuentaResponseDTO = cuentaService.actualizar(id, cuentaRequestDTO);
        return ResponseEntity.ok(cuentaResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        cuentaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
