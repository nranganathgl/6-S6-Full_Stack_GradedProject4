package com.gl.employee.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidRoleException.class})
    public ResponseEntity<Object> handleInvalidRoleException(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {InvalidRoleInputException.class})
    public ResponseEntity<Object> handleInvalidRoleInputException(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {DuplicateRoleException.class})
    public ResponseEntity<Object> handleDuplicateRoleException(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {InvalidEmployeeDataException.class})
    public ResponseEntity<Object> handleInvalidEmployeeDataException(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {NoEmployeesFoundException.class})
    public ResponseEntity<Object> handleNoEmployeesFoundException(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
