package com.ticketya.msvc.entradas.dto;

import com.ticketya.msvc.entradas.entities.EstadoEntrada;

import java.util.UUID;

public record EntradaEmitidaDTO(
        UUID id,
        UUID codigoQr,
        UUID idSector,
        UUID idAsiento,
        EstadoEntrada estado) {
}
