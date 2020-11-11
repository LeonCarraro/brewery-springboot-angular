package com.leoncarraro.breweryapi.service.exceptions;

public class ObjectAlreadyExistsException extends RuntimeException {

    public ObjectAlreadyExistsException(String msg) {
        super(msg);
    }

}
