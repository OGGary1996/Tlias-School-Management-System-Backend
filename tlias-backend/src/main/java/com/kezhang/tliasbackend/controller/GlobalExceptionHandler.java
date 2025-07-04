package com.kezhang.tliasbackend.controller;

import com.kezhang.tliasbackend.annotation.OperationLog;
import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.constant.ErrorCodeEnum;
import com.kezhang.tliasbackend.exception.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 设置响应状态码为500，表示服务器内部错误
    public Result<?> handleUnKnownException(Exception e){
        log.error("An unknown error occurred: {}", e.getMessage(), e);
        return Result.error(ErrorCodeEnum.UNKNOWN_ERROR.getCode(),ErrorCodeEnum.UNKNOWN_ERROR.getMessage());
    }

    /*
    * Handles SQL exceptions like SQLException
    * */
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 设置响应状态码为500，表示服务器内部错误
    public Result<?> handleSQLException(SQLException e){
        log.error("A database error occurred: {}", e.getMessage(), e);
        return Result.error(ErrorCodeEnum.DATABASE_ERROR.getCode(), ErrorCodeEnum.DATABASE_ERROR.getMessage());
    }

    /*
    * Handles IO exceptions like IOException
    * */
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 设置响应状态码为500，表示服务器内部错误
    public Result<?> handleIOException(IOException e){
        log.error("An IO error occurred: {}", e.getMessage(), e);
        return Result.error(ErrorCodeEnum.IO_ERROR.getCode(), ErrorCodeEnum.IO_ERROR.getMessage());
    }

    /*
    * Handles IllegalArgumentException exceptions
    * */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 设置响应状态码为400，表示请求错误
    public Result<?> handleIllegalArgumentException(IllegalArgumentException e){
        log.error("An illegal argument error occurred: {}", e.getMessage(), e);
        return Result.error(ErrorCodeEnum.ILLEGAL_ARGUMENT.getCode(), ErrorCodeEnum.ILLEGAL_ARGUMENT.getMessage());
    }


    /*
    * Handles department not found exceptions
    * */
    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 设置响应状态码为404，表示资源未找到
    public Result<?> handleDepartmentNotFoundException(DepartmentNotFoundException e){
        log.error("A department not found error occurred: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /*
    * Handles employee not found exceptions
    * */
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 设置响应状态码为404，表示资源未找到
    public Result<?> handleEmployeeNotFoundException(EmployeeNotFoundException e){
        log.error("An employee not found error occurred: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /*
    * Handles position not found exceptions
    * */
    @ExceptionHandler(PositionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 设置响应状态码为404，表示资源未找到
    public Result<?> handlePositionNotFoundException(PositionNotFoundException e){
        log.error("A position not found error occurred: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /*
    *  Handles subject not found exceptions
    * */
    @ExceptionHandler(SubjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 设置响应状态码为404，表示资源未找到
    public Result<?> handleSubjectNotFoundException(SubjectNotFoundException e){
        log.error("A subject not found error occurred: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /*
    * Handles class not found exceptions
    * */
    @ExceptionHandler(ClazzNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 设置响应状态码为404，表示资源未找到
    public Result<?> handleClazzNotFoundException(ClazzNotFoundException e){
        log.error("A class not found error occurred: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /*
    *  Handles clazz not allow to delete exceptions
    * */
    @ExceptionHandler(ClazzNotAllowToDeleteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 设置响应状态码为400，表示请求错误
    public Result<?> handleClazzNotAllowToDeleteException(ClazzNotAllowToDeleteException e){
        log.error("A class not allowed to delete error occurred: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /*
    * Handles student not found exceptions
    * */
    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 设置响应状态码为404，表示资源未找到
    public Result<?> handleStudentNotFoundException(StudentNotFoundException e){
        log.error("A student not found error occurred: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /*
    * Handles username or password error exceptions
    * */
    @ExceptionHandler(UsernameOrPasswordErrorException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 设置响应状态码为401，表示未授权
    public Result<?> handleUsernameOrPasswordErrorException(UsernameOrPasswordErrorException e){
        log.error("A username or password error occurred: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /*
    * Handles unauthorized user exceptions
    * */
    @ExceptionHandler(UnauthorizedUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 设置响应状态码为401，表示未授权
    public Result<?> handleUnauthorizedUserException(UnauthorizedUserException e){
        log.error("An unauthorized user error occurred: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /*
    * Handles login required exceptions
    * */
    @ExceptionHandler(LoginRequiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 设置响应状态码为401，表示未授权
    public Result<?> handleLoginRequiredException(LoginRequiredException e){
        log.error("A login required error occurred: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }
}
