package com.springsecurity.exception;

public class EmployeeAlreadyExistsException extends Throwable {
    public EmployeeAlreadyExistsException(String s) {
        super(s);

    }
}