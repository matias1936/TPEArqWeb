package com.ticketya.msvc.entradas.dto;

import com.ticketya.msvc.entradas.entities.EstadoEntrada;

import java.util.UUID;

public record EntradaResponseDTO(
        UUID id,
        UUID codigoQr,
        EstadoEntrada estado,
        EventoResumenDTO evento,
        SectorResumenDTO sector,
        UUID idAsiento,
        UUID idCompra,
        UUID idCliente) {
}
