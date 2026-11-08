package com.ticketya.msvc.entradas.exception;

import com.ticketya.msvc.entradas.dto.ErrorResponseDTO;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> noEncontrado(ResourceNotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, "ENTRADA_NO_ENCONTRADA", ex.getMessage(), null);
    }

    @ExceptionHandler(AccesoDenegadoException.class)
    public ResponseEntity<ErrorResponseDTO> accesoDenegado(AccesoDenegadoException ex) {
        return error(HttpStatus.FORBIDDEN, "ACCESO_DENEGADO", ex.getMessage(), null);
    }

    @ExceptionHandler(BusinessConflictException.class)
    public ResponseEntity<ErrorResponseDTO> conflicto(BusinessConflictException ex) {
        return error(HttpStatus.CONFLICT, ex.getCodigo(), ex.getMessage(), null);
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<ErrorResponseDTO> validacion(ValidacionException ex) {
        return error(HttpStatus.BAD_REQUEST, "VALIDACION", ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> cuerpoInvalido(MethodArgumentNotValidException ex) {
        List<String> detalles = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .toList();
        return error(HttpStatus.BAD_REQUEST, "VALIDACION", "Datos inválidos", detalles);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> parametrosInvalidos(ConstraintViolationException ex) {
        List<String> detalles = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .toList();
        return error(HttpStatus.BAD_REQUEST, "VALIDACION", "Parámetros inválidos", detalles);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorResponseDTO> parametrosInvalidosMetodo(HandlerMethodValidationException ex) {
        List<String> detalles = ex.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();
        return error(HttpStatus.BAD_REQUEST, "VALIDACION", "Parámetros inválidos", detalles);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDTO> faltaEncabezado(MissingRequestHeaderException ex) {
        return error(HttpStatus.UNAUTHORIZED, "FALTA_IDENTIFICACION",
                "Falta el encabezado " + ex.getHeaderName(), null);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> tipoIncorrecto(MethodArgumentTypeMismatchException ex) {
        return error(HttpStatus.BAD_REQUEST, "VALIDACION",
                "Valor inválido para el parámetro '" + ex.getName() + "'", null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> cuerpoIlegible(HttpMessageNotReadableException ex) {
        return error(HttpStatus.BAD_REQUEST, "VALIDACION", "El cuerpo de la solicitud no es válido", null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> inesperado(Exception ex) {
        log.error("Error inesperado", ex);
        return error(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR_INTERNO", "Error interno del servidor", null);
    }

    private ResponseEntity<ErrorResponseDTO> error(HttpStatus estado, String codigo,
                                                   String mensaje, List<String> detalles) {
        return ResponseEntity.status(estado)
                .body(new ErrorResponseDTO(codigo, mensaje, estado.value(), detalles));
    }
}
