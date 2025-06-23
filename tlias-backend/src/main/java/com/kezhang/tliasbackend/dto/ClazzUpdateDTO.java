package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for updating class information")
public class ClazzUpdateDTO {
    @Schema(description = "Class ID", example = "1")
    private Integer id; // Class ID
    @Schema(description = "Class name", example = "Mathematics 101")
    private String name; // Class name
    @Schema(description = "Class room", example = "Room 101")
    private String room; // Class room
    @Schema(description = "Class begin date", example = "2023-09-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate; // Class start date
    @Schema(description = "Class end date", example = "2024-06-30")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate; // Class end date
    @Schema(description = "Class master ID", example = "1")
    private Integer masterId; // Class master ID
    @Schema(description = "Subject ID associated with the class", example = "1")
    private Integer subjectId; // Subject ID associated with the class
} 