package com.gl.employee.exception;

public class NoEmployeesFoundException extends RuntimeException {

    private String errorMessage;

    public NoEmployeesFoundException() {

    }

    public NoEmployeesFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return errorMessage;
    }
}
