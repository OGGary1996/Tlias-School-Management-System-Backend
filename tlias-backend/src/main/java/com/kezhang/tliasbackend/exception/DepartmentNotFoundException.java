package com.kezhang.tliasbackend.exception;

import lombok.Getter;

@Getter
public class DepartmentNotFoundException extends RuntimeException {
    private final Integer code;

    public DepartmentNotFoundException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
