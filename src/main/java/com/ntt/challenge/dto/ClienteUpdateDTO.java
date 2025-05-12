package com.ntt.challenge.dto;

import com.ntt.challenge.model.Genero;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para actualizar los datos de un cliente")
public record ClienteUpdateDTO(
        @Schema(description = "Nueva contraseña del cliente", example = "nueva_contraseña_segura")
        String contrasena,
        @Schema(description = "Nuevo nombre del cliente", example = "Juan Pérez Actualizado")
        String nombre,
        @Schema(
                description = "Nuevo género del cliente",
                example = "FEMENINO",
                allowableValues = {"MASCULINO", "FEMENINO", "NO_BINARIO", "OTRO", "NO_ESPECIFICADO"}
        )
        Genero genero,
        @Schema(description = "Nueva edad del cliente", example = "31")
        Integer edad,
        @Schema(description = "Estado actualizado del cliente", example = "false")
        Boolean estado,
        @Schema(description = "Nueva dirección del cliente", example = "Av. Actualizada 456")
        String direccion,
        @Schema(description = "Nuevo número de teléfono del cliente", example = "+51999999999")
        String telefono
) {
}
