package com.kezhang.tliasbackend.exception;

import lombok.Getter;

@Getter
public class LoginRequiredException extends RuntimeException{
    private final Integer code;

    public LoginRequiredException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
