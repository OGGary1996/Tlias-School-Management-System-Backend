package com.kezhang.tliasbackend.exception;

import lombok.Getter;

@Getter
public class EmployeeNotFoundException extends RuntimeException{
    private final Integer code;

    public EmployeeNotFoundException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
