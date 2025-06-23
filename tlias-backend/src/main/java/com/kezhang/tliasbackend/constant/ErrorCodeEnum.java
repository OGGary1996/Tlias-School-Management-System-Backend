package com.kezhang.tliasbackend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    /*
    * Below are the error codes used in the application.
    * They are checked exceptions and should be thrown.
    * */
    UNKNOWN_ERROR(500, "Unknown Error, please try again later."),
    DATABASE_ERROR(600, "Database Error, please try again later."),
    IO_ERROR(700, "IO Error, please check your input."),

    /*
    * Below are the error codes for user-related operations.
    * These are RuntimeExceptions and should not be caught manually.
    * */
    NETWORK_ERROR(800, "Network Error, please check your network connection."),
    DEPARTMENT_NOT_FOUND(1001, "Department not found."),
    EMPLOYEE_NOT_FOUND(1002, "Employee not found."),
    POSITION_NOT_FOUND(1003, "Position not found."),
    SUBJECT_NOT_FOUND(1004, "Subject not found."),

    ;


    private final Integer code;
    private final String message;

}
