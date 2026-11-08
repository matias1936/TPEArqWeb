package com.ticketya.msvc.entradas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventoResumenDTO(
        @NotNull UUID id,
        @NotBlank String nombre,
        @NotNull LocalDateTime comienzaEn) {
}
