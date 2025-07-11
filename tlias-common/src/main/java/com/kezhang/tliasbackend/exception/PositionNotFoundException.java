package com.kezhang.tliasbackend.exception;

import lombok.Getter;

@Getter
public class PositionNotFoundException extends RuntimeException {
    private final Integer code;

    public PositionNotFoundException(Integer code,String message){
        super(message);
        this.code = code;
    }
} 