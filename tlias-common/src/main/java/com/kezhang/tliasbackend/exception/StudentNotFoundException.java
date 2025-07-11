package com.kezhang.tliasbackend.exception;

import lombok.Getter;

@Getter
public class StudentNotFoundException extends RuntimeException{
    private final Integer code;

    public StudentNotFoundException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
