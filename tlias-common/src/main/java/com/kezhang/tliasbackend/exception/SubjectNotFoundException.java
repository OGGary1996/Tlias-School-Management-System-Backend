package com.kezhang.tliasbackend.exception;

import lombok.Getter;

@Getter
public class SubjectNotFoundException extends RuntimeException{
    private final Integer code;

    public SubjectNotFoundException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
