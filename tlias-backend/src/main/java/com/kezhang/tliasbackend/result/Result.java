package com.kezhang.tliasbackend.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Generic Result class for API responses")
public class Result<T> {
    @Schema(description = "Status code of the response",example = "200")
    private Integer code; // HTTP status code
    @Schema(description = "Response message", example = "Success")
    private String message; // Response message
    private T data; // Response data

    public static <T> Result<T> success(T data){
        return Result.<T>builder()
                .code(200)
                .message("Success")
                .data(data)
                .build();
    }

    public static <T> Result<T> error(Integer code ,String message){
        return Result.<T>builder()
                .code(code)
                .message(message)
                .data(null)
                .build();
    }

}
