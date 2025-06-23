package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for displaying class information (for frontend display)")
public class ClazzDisplayDTO {
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
    @Schema(description = "Class master name", example = "John Doe")
    private String masterName; // Class master name
    @Schema(description = "Subject name associated with the class", example = "Mathematics")
    private String subjectName; // Subject name associated with the class
    @Schema(description = "Class create time", example = "2023-09-01T10:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; // Callback time for the class
    @Schema(description = "Class update time", example = "2023-09-01T10:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; // Last update time for the class
} 