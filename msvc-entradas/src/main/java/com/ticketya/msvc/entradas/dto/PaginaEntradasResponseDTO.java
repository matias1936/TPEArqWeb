package com.ticketya.msvc.entradas.dto;

import java.util.List;

public record PaginaEntradasResponseDTO(
        List<EntradaResponseDTO> content,
        int page,
        int size,
        long totalElements,
        int totalPages) {
}
