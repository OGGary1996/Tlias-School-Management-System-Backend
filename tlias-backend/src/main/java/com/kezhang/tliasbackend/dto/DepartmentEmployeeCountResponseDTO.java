package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for Department Employee Count Response")
public class DepartmentEmployeeCountResponseDTO {
    @Schema(description = "List of department name")
    private List<String> departmentNameList;
    @Schema(description = "List of employee count corresponding to each department")
    private List<Long> employeeCountList;
}
