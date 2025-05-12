package com.ntt.challenge.dto;

import com.ntt.challenge.model.Genero;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "DTO de respuesta con los datos de un cliente")
public record ClienteResponseDTO(
        @Schema(description = "Identificador único del cliente", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(description = "Código único del cliente", example = "12345678")
        String codigoCliente, // ClienteId -> Documento
        @Schema(description = "Nombre completo del cliente", example = "Juan Pérez")
        String nombre,
        @Schema(
                description = "Género del cliente",
                example = "MASCULINO",
                allowableValues = {"MASCULINO", "FEMENINO", "NO_BINARIO", "OTRO", "NO_ESPECIFICADO"}
        )
        Genero genero,
        @Schema(description = "Edad del cliente", example = "30")
        Integer edad,
        @Schema(description = "Documento Nacional de Identidad del cliente", example = "87654321")
        String dni,
        @Schema(description = "Dirección física del cliente", example = "Av. Siempre Viva 123")
        String direccion,
        @Schema(description = "Número de teléfono del cliente", example = "+51987654321")
        String telefono,
        @Schema(description = "Estado actual del cliente", example = "true")
        Boolean estado
) {
}
