package com.leoncarraro.breweryapi.controller.exceptions;

import com.leoncarraro.breweryapi.service.exceptions.BadRequestException;
import com.leoncarraro.breweryapi.service.exceptions.ObjectAlreadyExistsException;
import com.leoncarraro.breweryapi.service.exceptions.ObjectNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(value = { ObjectAlreadyExistsException.class })
    public ResponseEntity<StandardError> objectAlreadyExistsExceptionHandler(ObjectAlreadyExistsException e) {
        StandardError error = new StandardError(
                OffsetDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                Collections.singletonList(new StandardError.Error(e.getMessage()))
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(value = { BadRequestException.class })
    public ResponseEntity<StandardError> badRequestExceptionHandler(BadRequestException e) {
        StandardError error = new StandardError(
                OffsetDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                Collections.singletonList(new StandardError.Error(e.getMessage()))
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
    public ResponseEntity<StandardError> methodArgumentTypeMismatchExceptionHandler() {
        String message = "A URI da requisição contém 1 ou mais atributos inválidos! Por favor, " +
                "verifique erro de sintaxe e tente novamente.";

        StandardError error = new StandardError(
                OffsetDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                Collections.singletonList(new StandardError.Error(message))
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<StandardError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        StandardError error = new StandardError(
                OffsetDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errors.stream().map(StandardError.Error::new).collect(Collectors.toList())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
