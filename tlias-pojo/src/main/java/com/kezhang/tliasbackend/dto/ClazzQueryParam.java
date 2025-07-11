package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entity representing query parameters for class information")
public class ClazzQueryParam {
    @Schema(description = "Name of the class")
    private String name;
    @Schema(description = "Begin date for the class")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Used for parsing date format
    private LocalDate beginDate;
    @Schema(description = "End date for the class")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Used for parsing date format
    private LocalDate endDate;

    @Schema(description = "Page number for pagination")
    @NotNull(message = "Page number cannot be null")
    private Integer page;
    @Schema(description = "Size of each page for pagination")
    @NotNull(message = "Page size cannot be null")
    private Integer pageSize;
}
