package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Department Create DTO")
public class DepartmentCreateDTO {
    @Schema(description = "Name of the department", example = "Human Resources")
    @NotNull(message = "Department name cannot be null")
    @NotBlank(message = "Department name cannot be blank")
    private String name;
}
