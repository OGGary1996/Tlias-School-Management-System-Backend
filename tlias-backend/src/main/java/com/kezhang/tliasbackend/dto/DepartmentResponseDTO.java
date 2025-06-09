package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Department Response DTO")
public class DepartmentResponseDTO {
    @Schema(description = "ID of the department", example = "1")
    private Integer id;

    @Schema(description = "Name of the department", example = "Human Resources")
    private String name;

    @Schema(description = "Creation time of the department", example = "2023-10-01T12:00:00Z")
    private String createTime;

    @Schema(description = "Last update time of the department", example = "2023-10-01T12:00:00Z")
    private String updateTime;
}
