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
@Schema(description = "Position Update DTO")
public class PositionUpdateDTO {
    @Schema(description = "id of the position", example = "1")
    @NotNull(message = "Position ID cannot be null")
    private Integer id;

    @Schema(description = "Name of the position", example = "Software Engineer")
    @NotNull(message = "Position name cannot be null")
    @NotBlank(message = "Position name cannot be blank")
    private String name;

    @Schema(description = "Description of the position", example = "Develop and maintain software applications")
    private String description;
} 