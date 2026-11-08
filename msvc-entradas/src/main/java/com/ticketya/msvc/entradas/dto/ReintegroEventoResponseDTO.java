package com.ticketya.msvc.entradas.dto;

import java.util.UUID;

public record ReintegroEventoResponseDTO(UUID idEvento, int entradasMarcadas) {
}
