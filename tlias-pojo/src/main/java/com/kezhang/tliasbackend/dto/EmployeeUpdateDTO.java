package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "DTO for update&callback a exist employee")
public class EmployeeUpdateDTO {
    @Schema(description = "Employee's ID", example = "1")
    @NotNull(message = "Employee ID cannot be null")
    private Integer id; // 员工ID

    @Schema(description = "Employee's username", example = "JohnDoe")
    private String username; // 员工用户名

    @Schema(description = "Employee's name", example = "John Doe")
    private String name; // 员工姓名

    @Schema(description = "Employee's gender", example = "1", allowableValues = "0, 1")
    private Integer gender; // 员工性别

    @Schema(description = "Employee's phone number", example = "1234567890")
    private String phone; // 员工手机号

    @Schema(description = "Employee's job title ID", example = "1")
    private Integer jobTitle; // 来自position表的ID

    @Schema(description = "Employee's department ID", example = "1")
    private Integer departmentId; // 来自department表的ID

    @Schema(description = "Employee's salary", example = "5000.00")
    private BigDecimal salary;

    @Schema(description = "Employee's entry date", example = "2023-12-31")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 用于解析日期格式
    private LocalDate entryDate; // 员工入职日期

    @Schema(description = "Employee's profile image URL", example = "http://example.com/image.jpg")
    private String image; // 员工头像图片地址

    @Schema(description = "Employee's work history list")
    private List<EmployeeHistoryUpdateDTO> employeeHistoryUpdateDTOList; // 员工历史信息列表
}
