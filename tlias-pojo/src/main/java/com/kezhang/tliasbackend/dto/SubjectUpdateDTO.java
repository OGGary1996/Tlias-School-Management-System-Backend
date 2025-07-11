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
@Schema(description = "Subject Update DTO")
public class SubjectUpdateDTO {
    @Schema(description = "id of the subject", example = "1")
    @NotNull(message = "Subject ID cannot be null")
    private Integer id;

    @Schema(description = "Name of the subject", example = "Mathematics")
    @NotNull(message = "Subject name cannot be null")
    @NotBlank(message = "Subject name cannot be blank")
    private String name;

    @Schema(description = "Description of the subject", example = "Advanced mathematics course covering calculus and algebra")
    private String description;
} 