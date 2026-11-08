package com.ticketya.msvc.entradas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/** Un lugar comprado. idAsiento es nulo en sectores no numerados. */
public record LugarAEmitirDTO(
        @NotNull UUID idSector,
        @NotBlank String nombreSector,
        UUID idAsiento) {
}
