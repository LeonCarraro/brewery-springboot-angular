package com.leoncarraro.breweryapi.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class StandardError {

    private final OffsetDateTime timestamp;
    private final Integer httpStatusCode;
    private final String httpStatusDescription;
    private final List<Error> errors;

    @Getter
    @AllArgsConstructor
    static class Error {

        private final String description;

    }

}
