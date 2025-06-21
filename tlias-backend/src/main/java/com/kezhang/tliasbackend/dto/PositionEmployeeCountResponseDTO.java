package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionEmployeeCountResponseDTO {
    @Schema(description = "List of PositionEmployeeCountDTO", example = "[{\"positionName\": \"Software Engineer\", \"employeeCount\": 25}]")
    private List<String> positionNameList;
    @Schema(description = "List of Employee Counts", example = "[25, 30, 15]")
    private List<Integer> employeeCountList;
}
