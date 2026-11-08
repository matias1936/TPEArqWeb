package com.ticketya.msvc.entradas.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponseDTO(String codigo, String mensaje, int estado, List<String> detalles) {
}
