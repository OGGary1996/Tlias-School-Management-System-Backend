package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "DTO for update&callback a exist employee's history")
public class EmployeeHistoryUpdateDTO {

    @Schema(description = "Employee's previous job start date", example = "2023-12-31")
    private LocalDate startDate; // 开始日期

    @Schema(description = "Employee's previous job end date", example = "2023-12-31")
    private LocalDate endDate; // 结束日期

    @Schema(description = "Employee's previous job title")
    private String jobTitle; // 职位名称

    @Schema(description = "Employee's previous company name")
    private String companyName; // 公司名称

    @Schema(description = "Employee's previous job description")
    private String employeeId; // 职位描述
}
