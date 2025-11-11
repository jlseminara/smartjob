package com.smartjob.usersbackend.adapter.in.web;

import com.smartjob.usersbackend.adapter.in.web.models.ErrorResponse;
import com.smartjob.usersbackend.exceptions.DataCreationException;
import com.smartjob.usersbackend.exceptions.DataFormatErrorException;
import com.smartjob.usersbackend.exceptions.DataIntegrityException;
import com.smartjob.usersbackend.exceptions.DataRetrieveException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionManagement {

    @ExceptionHandler(DataRetrieveException.class)
    public ResponseEntity<ErrorResponse> handleDataRetrieveException(DataRetrieveException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(new ErrorResponse().mensaje(ex.getMessage()));
    }

    @ExceptionHandler(DataCreationException.class)
    public ResponseEntity<ErrorResponse> handleCreationException(DataCreationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(new ErrorResponse().mensaje(ex.getMessage()));
    }

    @ExceptionHandler(DataFormatErrorException.class)
    public ResponseEntity<ErrorResponse> handleDataFormatException(DataFormatErrorException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ErrorResponse().mensaje(ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityException(DataIntegrityException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ErrorResponse().mensaje(ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ErrorResponse().mensaje("El formato de la peticion no es la esperada"));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageMediaNotSupported(HttpMediaTypeNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ErrorResponse().mensaje("El formato de la peticion no es la esperada"));
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageMediaNotNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ErrorResponse().mensaje("El formato de la peticion no es la esperada"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ErrorResponse().mensaje("El formato o peticion no soportada"));
    }

}
