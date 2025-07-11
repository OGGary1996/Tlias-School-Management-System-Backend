package com.kezhang.tliasbackend.exception;

import lombok.Getter;

@Getter
public class UsernameOrPasswordErrorException extends RuntimeException{
    private final Integer code;

    public UsernameOrPasswordErrorException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
