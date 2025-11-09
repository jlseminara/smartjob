package com.smartjob.usersbackend.adapter.in.web;

import com.smartjob.usersbackend.adapter.in.web.models.ErrorResponse;
import com.smartjob.usersbackend.exceptions.DataCreationException;
import com.smartjob.usersbackend.exceptions.DataFormatErrorException;
import com.smartjob.usersbackend.exceptions.DataRetrieveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RestControllerAdvice
public class ControllerExceptionManagement extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataRetrieveException.class)
    public ResponseEntity<ErrorResponse> handleDataRetrieveException(DataRetrieveException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(
                        new ErrorResponse().titulo("Error while retrieving data")
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .mensaje(ex.getMessage())
                );
    }

    @ExceptionHandler(DataCreationException.class)
    public ResponseEntity<ErrorResponse> handleCreationException(DataCreationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(
                        new ErrorResponse().titulo("Error while inserting data in database")
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .mensaje(ex.getMessage())
                );
    }

    @ExceptionHandler(DataFormatErrorException.class)
    public ResponseEntity<ErrorResponse> handleDataFormatException(DataFormatErrorException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(
                        new ErrorResponse().titulo("Bad data format")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .mensaje(ex.getMessage())
                );
    }


}
