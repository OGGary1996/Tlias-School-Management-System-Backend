package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "EmployeeResponseDTO", description = "Response DTO for Employee")
public class EmployeeResponseDTO {
    @Schema(description = "ID of employee", example = "1")
    private Integer id;              // 来自 e.id
    @Schema(description = "Name of employee", example = "John Doe")
    private String name;         // 来自 e.username
    @Schema(description = "Gender of employee", example = "1/0")
    private Integer gender;          // 来自 e.gender
    @Schema(description = "Profile image of employee")
    private String image;            // 来自 e.image
    @Schema(description = "Department of employee")
    private String departmentName;   // 来自 d.name AS departmentName（注意别名对应）
    @Schema(description = "Job title of employee", example = "Lecturer/Professor")
    private String jobTitle;        // 来自 p.name AS jobTitle（注意别名对应）
    @Schema(description = "Entry Date of employee", example = " 2023-01-01")
    private LocalDate entryDate;     // 来自 e.entry_date
    @Schema(description = "Last update time of employee", example = " 2023-01-01T12:00:00")
    private LocalDateTime updateTime;// 来自 e.update_time
}
