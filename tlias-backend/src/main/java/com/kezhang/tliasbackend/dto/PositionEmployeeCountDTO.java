package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "PositionEmployeeCountDTO", description = "DTO for Position Employee Count")
public class PositionEmployeeCountDTO {
    @Schema(description = "Position Name", example = "Software Engineer")
    private String positionName;
    @Schema(description = "Employee Count", example = "25")
    private Integer employeeCount;
}
