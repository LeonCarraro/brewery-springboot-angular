package com.leoncarraro.breweryapi.controller.exceptions;

import com.leoncarraro.breweryapi.controller.exceptions.StandardError;
import com.leoncarraro.breweryapi.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = { ObjectNotFoundException.class })
    public ResponseEntity<StandardError> objectNotFoundExceptionHandler(ObjectNotFoundException e) {
        StandardError error = new StandardError(
                OffsetDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                Collections.singletonList(new StandardError.Error(e.getMessage()))
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
