package com.ntt.challenge.dto;

import com.ntt.challenge.model.Genero;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "DTO para crear un cliente")
public record ClienteRequestDTO(
        @NotBlank(message = "El código de cliente no puede estar vacío")
        @Schema(description = "Código único del cliente", example = "12345678")
        String codigoCliente,// ClienteId -> Documento
        @NotBlank(message = "La contraseña no puede estar vacía")
        @Schema(description = "Contraseña del cliente para acceso a la plataforma", example = "mi_contraseña_segura")
        String contrasena,
        @NotBlank(message = "El nombre no puede estar vacío")
        @Schema(description = "Nombre completo del cliente", example = "Juan Pérez")
        String nombre,

        @Schema(
                description = "Género del cliente. Por defecto es NO_ESPECIFICADO si no se proporciona.",
                example = "MASCULINO",
                allowableValues = {"MASCULINO", "FEMENINO", "NO_BINARIO", "OTRO", "NO_ESPECIFICADO"},
                defaultValue = "NO_ESPECIFICADO"
        )
        Genero genero,
        @NotBlank(message = "El DNI no puede estar vacío")
        @Schema(description = "Documento Nacional de Identidad del cliente", example = "87654321")
        String dni,

        @PositiveOrZero(message = "La edad no puede ser negativa")
        @Schema(description = "Edad del cliente", example = "30")
        Integer edad,

        //Opcional
        @Schema(description = "Estado del cliente. Por defecto es true si no se especifica.", example = "true", defaultValue = "true")
        Boolean estado,

        @Schema(description = "Dirección física del cliente", example = "Av. Siempre Viva 123")
        String direccion,

        @Schema(description = "Número de teléfono del cliente", example = "+51987654321")
        String telefono
) {
}
