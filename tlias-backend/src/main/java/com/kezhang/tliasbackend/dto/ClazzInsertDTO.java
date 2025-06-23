package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ClazzInsertDTO", description = "DTO for inserting a class")
public class ClazzInsertDTO {
    @Schema(description = "Class name", example = "Java Development - Cohort 10")
    @NotBlank(message = "Class name cannot be blank")
    @NotNull(message = "Class name cannot be null")
    private String name; // Class name
    @Schema(description = "Class room", example = "CS-301")
    @NotBlank(message = "Class room cannot be blank")
    @NotNull(message = "Class room cannot be null")
    private String room; // Class room
    @Schema(description = "Class start date", example = "2023-09-01")
    @NotNull(message = "Class start date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate; // Class start date
    @Schema(description = "Class end date", example = "2024-06-30")
    @NotNull(message = "Class end date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate; // Class end date
    @Schema(description = "Employee's ID", example = "1")
    @NotNull(message = "Master ID cannot be null")
    private Integer masterId; // Master ID
    @Schema(description = "Subject's ID", example = "1")
    @NotNull(message = "Subject ID cannot be null")
    private Integer subjectId; // Subject ID
}
