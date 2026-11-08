package com.ticketya.msvc.entradas.exception;

public class BusinessConflictException extends RuntimeException {

    private final String codigo;

    public BusinessConflictException(String codigo, String mensaje) {
        super(mensaje);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
