package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Parameters for querying students")
public class StudentQueryParam {
    @Schema(description = "Student name", example = "John Doe")
    private String name; // Student's name

    @Schema(description = "Student degree", example = "1")
    private Integer degree; // 学历

    @Schema(description = "Student class ID", example = "101")
    private Integer clazzId; // Class ID the student belongs to

    @Schema(description = "Current page number for pagination", example = "1")
    private Integer page; // Current page number for pagination

    @Schema(description = "Number of records per page for pagination", example = "10")
    private Integer pageSize; // Number of records per page for pagination
}
