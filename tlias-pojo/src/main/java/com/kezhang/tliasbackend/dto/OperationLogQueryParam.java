package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Operation Log Query DTO")
public class OperationLogQueryParam {
    @Schema(description = "Page number", example = "1")
    @NotNull(message = "Page number cannot be null")
    private Integer page;
    @Schema(description = "Page size", example = "10")
    @NotNull(message = "Page size cannot be null")
    private Integer pageSize;
    @Schema(description = "Operation Employee ID", example = "123")
    private Integer operateEmpId;
    @Schema(description = "Operation Employee Name", example = "John Doe")
    private String operateEmpName;
    @Schema(description = "Operation Time Start", example = "2023-01-01T00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime operateTime;
    @Schema(description = "Operation class name", example = "com.kezhang.tliasbackend.service.UserService")
    private String javaClassName;
    @Schema(description = "Operation method name", example = "getUserById")
    private String javaMethodName;
    @Schema(description = "Is Success", example = "1")
    private Integer isSuccess; // 0:失败，1:成功
}
