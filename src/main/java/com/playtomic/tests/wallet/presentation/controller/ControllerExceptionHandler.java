package com.playtomic.tests.wallet.presentation.controller;

import com.playtomic.tests.wallet.presentation.response.ErrorMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    private ResponseEntity<ErrorMessage> sendErrorMessage(HttpStatus httpStatus, String exceptionMessage, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                httpStatus.value(),
                LocalDate.now(),
                exceptionMessage,
                request.getDescription(false));
        return new ResponseEntity<>(message, httpStatus);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return sendErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }
}
