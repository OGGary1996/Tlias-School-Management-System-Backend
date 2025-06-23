package com.kezhang.tliasbackend.controller;

import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.constant.ErrorCodeEnum;
import com.kezhang.tliasbackend.exception.DepartmentNotFoundException;
import com.kezhang.tliasbackend.exception.EmployeeNotFoundException;
import com.kezhang.tliasbackend.exception.NetworkException;
import com.kezhang.tliasbackend.exception.PositionNotFoundException;
import com.kezhang.tliasbackend.exception.SubjectNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
@Tag(name = "GlobalExceptionHandler", description = "Global exception handler for the application")
public class GlobalExceptionHandler {
    /*
    * Handles unknow exception like Exception
    * */
    @ExceptionHandler(Exception.class)
    public Result<?> handleUnKnownException(Exception e){
        log.error("An unknown error occurred: {}", e.getMessage(), e);
        return Result.error(ErrorCodeEnum.UNKNOWN_ERROR.getCode(),ErrorCodeEnum.UNKNOWN_ERROR.getMessage());
    }

    /*
    * Handles SQL exceptions like SQLException
    * */
    @ExceptionHandler(SQLException.class)
    public Result<?> handleSQLException(SQLException e){
        log.error("A database error occurred: {}", e.getMessage(), e);
        return Result.error(ErrorCodeEnum.DATABASE_ERROR.getCode(), ErrorCodeEnum.DATABASE_ERROR.getMessage());
    }

    /*
    * Handles IO exceptions like IOException
    * */
    @ExceptionHandler(IOException.class)
    public Result<?> handleIOException(IOException e){
        log.error("An IO error occurred: {}", e.getMessage(), e);
        return Result.error(ErrorCodeEnum.IO_ERROR.getCode(), ErrorCodeEnum.IO_ERROR.getMessage());
    }

    /*
    * Handles network exceptions
    * */
    @ExceptionHandler(NetworkException.class)
    public Result<?> handleNetworkException(NetworkException e){
        log.error("A network error occurred: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /*
    * Handles department not found exceptions
    * */
    @ExceptionHandler(DepartmentNotFoundException.class)
    public Result<?> handleDepartmentNotFoundException(DepartmentNotFoundException e){
        log.error("A department not found error occurred: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /*
    * Handles employee not found exceptions
    * */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public Result<?> handleEmployeeNotFoundException(EmployeeNotFoundException e){
        log.error("An employee not found error occurred: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /*
    * Handles position not found exceptions
    * */
    @ExceptionHandler(PositionNotFoundException.class)
    public Result<?> handlePositionNotFoundException(PositionNotFoundException e){
        log.error("A position not found error occurred: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /*
    * Handles subject not found exceptions
    * */
    @ExceptionHandler(SubjectNotFoundException.class)
    public Result<?> handleSubjectNotFoundException(SubjectNotFoundException e){
        log.error("A subject not found error occurred: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }
}
