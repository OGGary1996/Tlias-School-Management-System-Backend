package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "DTO for querying employees with optional filters")
public class EmployeeQueryParam {
    @Schema(description = "Name of the employee", example = "John Doe")
    private String name; // 员工姓名

    @Schema(description = "Gender of the employee", example = "1", allowableValues = "0, 1")
    private Integer gender; // 员工性别

    @Schema(description = "Start date for employee's start date filter", example = "2023-12-31")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 用于解析日期格式
    private LocalDate startDate; // 员工入职开始日期

    @Schema(description = "End date for employee's end date filter", example = "2023-12-31")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 用于解析日期格式
    private LocalDate endDate; // 员工入职结束日期

    @Schema(description = "Current page number, starting from 1,default = 1", example = "1")
    @NotNull(message = "Page number cannot be null")
    private Integer page = 1; // 页码，默认值为1

    @Schema(description = "Number of records per page,default = 10", example = "10")
    @NotNull(message = "Page size cannot be null")
    private Integer pageSize = 10; // 每页显示条数，默认值为10
}
