package com.kezhang.tliasbackend.exception;

import lombok.Getter;

@Getter
public class ClazzNotFoundException extends RuntimeException{
    private final Integer code;

    public ClazzNotFoundException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
