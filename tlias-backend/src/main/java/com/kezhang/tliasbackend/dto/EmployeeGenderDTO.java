package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "EmployeeGenderDTO", description = "DTO for Employee's gender distribution")
public class EmployeeGenderDTO {
    @Schema(description= "Employee gender", example="0,1")
    private String name; // Employee's gender
    @Schema(description = "Count of employees with that gender", example = "100")
    private Integer value; // Count of employees with that
}
