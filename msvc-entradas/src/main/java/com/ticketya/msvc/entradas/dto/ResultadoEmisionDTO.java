package com.ticketya.msvc.entradas.dto;

/**
 * Resultado interno de la emisión. "creada" distingue una emisión nueva (HTTP 201)
 * de un reintento idempotente que devuelve las entradas ya emitidas (HTTP 200).
 */
public record ResultadoEmisionDTO(EmisionResponseDTO emision, boolean creada) {
}
