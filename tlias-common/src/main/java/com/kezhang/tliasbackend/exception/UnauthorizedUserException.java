package com.kezhang.tliasbackend.exception;

import lombok.Getter;

@Getter
public class UnauthorizedUserException extends RuntimeException{
    private final Integer code;

    public UnauthorizedUserException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
