package com.ntt.challenge.exception;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Error de validación",
                errors
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();

        // Verificar si es una excepción de propiedad desconocida
        if (ex.getCause() instanceof UnrecognizedPropertyException) {
            UnrecognizedPropertyException unrecognizedProperty = (UnrecognizedPropertyException) ex.getCause();
            String propertyName = unrecognizedProperty.getPropertyName();
            String message = "La propiedad '" + propertyName + "' no está definida en el DTO y no debe estar presente";
            errors.put(propertyName, message);
        } else {
            // Para otros errores de formato JSON
            errors.put("error", "Error en el formato del JSON: " + ex.getMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Error en el formato de la solicitud",
                errors
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Clase para representar la respuesta de error
    public static class ErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String message;
        private Map<String, String> errors;

        public ErrorResponse(LocalDateTime timestamp, int status, String message, Map<String, String> errors) {
            this.timestamp = timestamp;
            this.status = status;
            this.message = message;
            this.errors = errors;
        }

        // Getters y setters
        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Map<String, String> getErrors() {
            return errors;
        }

        public void setErrors(Map<String, String> errors) {
            this.errors = errors;
        }
    }
}