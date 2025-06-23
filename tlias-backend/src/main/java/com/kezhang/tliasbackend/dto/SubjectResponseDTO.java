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
@Schema(description = "Subject Response DTO")
public class SubjectResponseDTO {
    @Schema(description = "ID of the subject", example = "1")
    private Integer id;

    @Schema(description = "Name of the subject", example = "Mathematics")
    private String name;

    @Schema(description = "Description of the subject", example = "Advanced mathematics course covering calculus and algebra")
    private String description;

    @Schema(description = "Creation time of the subject", example = "2023-10-01T12:00:00Z")
    private LocalDateTime createTime;

    @Schema(description = "Last update time of the subject", example = "2023-10-01T12:00:00Z")
    private LocalDateTime updateTime;
} 