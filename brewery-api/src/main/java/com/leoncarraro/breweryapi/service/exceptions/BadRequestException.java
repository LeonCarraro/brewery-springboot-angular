package com.leoncarraro.breweryapi.service.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String msg) {
        super(msg);
    }

}
