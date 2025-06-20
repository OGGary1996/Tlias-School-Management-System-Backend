package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Position Response DTO")
public class PositionResponseDTO {
    @Schema(description = "ID of the position", example = "1")
    private Integer id;

    @Schema(description = "Name of the position", example = "Software Engineer")
    private String name;

    @Schema(description = "Description of the position", example = "Develop and maintain software applications")
    private String description;

    @Schema(description = "Creation time of the position", example = "2023-10-01T12:00:00Z")
    private LocalDateTime createTime;

    @Schema(description = "Last update time of the position", example = "2023-10-01T12:00:00Z")
    private LocalDateTime updateTime;
} 