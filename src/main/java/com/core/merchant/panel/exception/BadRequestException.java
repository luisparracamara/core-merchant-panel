package com.core.merchant.panel.exception;

public class BadRequestException extends DomainException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable ex) {
        super(message, ex);
    }
}
