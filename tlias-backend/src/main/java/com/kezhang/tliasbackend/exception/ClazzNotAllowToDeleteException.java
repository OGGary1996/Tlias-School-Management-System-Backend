package com.kezhang.tliasbackend.exception;

import lombok.Getter;

@Getter
public class ClazzNotAllowToDeleteException extends RuntimeException{
    private final Integer code;

    public ClazzNotAllowToDeleteException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
