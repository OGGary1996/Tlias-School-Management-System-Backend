package com.kezhang.tliasbackend.exception;

import lombok.Getter;

@Getter
public class NetworkException extends RuntimeException{
    private final Integer code;

    public NetworkException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
