package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Student Degree Report Data Transfer Object")
public class StudentDegreeReportDTO {
    @Schema(description = "Degree" ,example = "Bachelor's Degree")
    private String name;
    @Schema(description = "Count of students with this degree", example = "150")
    private Long value;
}
