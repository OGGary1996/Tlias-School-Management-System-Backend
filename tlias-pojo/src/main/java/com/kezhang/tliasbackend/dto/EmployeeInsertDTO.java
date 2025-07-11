package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "DTO for inserting a new employee")
public class EmployeeInsertDTO {

    @Schema(description = "Employee's username", example = "JohnDoe")
    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be blank")
    private String username; // 员工用户名

    @Schema(description = "Employee's name", example = "John Doe")
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name; // 员工姓名

    @Schema(description = "Employee's gender", example = "1", allowableValues = "0, 1")
    @NotNull(message = "Gender cannot be null")
    private Integer gender; // 员工性别

    @Schema(description = "Employee's phone number", example = "1234567890")
    @NotNull(message = "Phone number cannot be null")
    @NotBlank(message = "Phone number cannot be blank")
    private String phone; // 员工手机号

    @Schema(description = "Employee's job title ID", example = "1")
    @NotNull(message = "Job title ID cannot be null")
    private Integer jobTitle; // 来自position表的ID

    @Schema(description = "Employee's department ID", example = "1")
    @NotNull(message = "Department ID cannot be null")
    private Integer departmentId; // 来自department表的ID

    @Schema(description = "Employee's salary", example = "5000.00")
    @NotNull(message = "Salary cannot be null")
    private BigDecimal salary;

    @Schema(description = "Employee's profile image URL", example = "http://example.com/image.jpg")
    private String image; // 员工头像图片地址

    @Schema(description = "Employee's entry date", example = "2023-12-31")
    @NotNull(message = "Entry date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 用于解析日期格式
    private LocalDate entryDate; // 员工入职日期

    @Schema(description = "Employee's history")
    private List<EmployeeHistoryInsertDTO> employeeHistoryInsertDTOList; // 员工历史记录列表
}
