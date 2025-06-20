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
@Schema(description = "Position Create DTO")
public class PositionInsertDTO {
    @Schema(description = "Name of the position", example = "Software Engineer")
    @NotNull(message = "Position name cannot be null")
    @NotBlank(message = "Position name cannot be blank")
    private String name;

    @Schema(description = "Description of the position", example = "Develop and maintain software applications")
    private String description;
} 