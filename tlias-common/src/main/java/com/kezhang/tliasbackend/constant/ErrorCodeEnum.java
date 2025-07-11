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
    UNKNOWN_ERROR(600, "Unknown Error, please try again later."),
    DATABASE_ERROR(601, "Database Error, please try again later."),
    IO_ERROR(602, "IO Error, please check your input."),
    ILLEGAL_ARGUMENT(603, "Illegal Argument, please check your input."),

    /*
    * Below are the error codes for user-related operations.
    * These are RuntimeExceptions and should not be caught manually.
    * */
    DEPARTMENT_NOT_FOUND(1001, "Department not found."),
    EMPLOYEE_NOT_FOUND(1002, "Employee not found."),
    POSITION_NOT_FOUND(1003, "Position not found."),
    SUBJECT_NOT_FOUND(1004, "Subject not found."),
    CLAZZ_NOT_FOUND(1005, "Class not found."),
    CLAZZ_NOT_ALLOWED_TO_DELETE(1006, "Class cannot be deleted, it's ongoing."),
    STUDENT_NOT_FOUND(1007, "Student not found."),
    USERNAME_OR_PASSWORD_ERROR(2001, "Username or password is incorrect."),
    UNAUTHORIZED_USER(2002, "Unauthorized access, please login first."),
    LOGIN_REQUIRED(2003, "Login is required to access this resource."),


    ;

    private final Integer code;
    private final String message;

}
