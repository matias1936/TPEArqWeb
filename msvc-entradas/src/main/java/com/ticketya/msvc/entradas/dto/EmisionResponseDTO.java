package com.ticketya.msvc.entradas.dto;

import java.util.List;
import java.util.UUID;

public record EmisionResponseDTO(UUID idCompra, List<EntradaEmitidaDTO> entradas) {
}
