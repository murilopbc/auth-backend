package com.murilo.auth.dtos.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Acesso não autorizado");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
