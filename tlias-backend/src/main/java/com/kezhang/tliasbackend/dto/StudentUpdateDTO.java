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
@Schema(description = "Display DTO for Student entity")
public class StudentUpdateDTO {
    @Schema(description = "Unique identifier for the student", example = "1")
    private Integer id;
    @Schema(description = "Name of the student", example = "John Doe")
    private String name;
    @Schema(description = "Account number of student", example = "123456789")
    private String accountNumber;
    @Schema(description = "Gender of the student", example = "1")
    private Integer gender;
    @Schema(description = "Phone number of the student", example = "123-456-7890")
    private String phone;
    @Schema(description = "ID card number of the student", example = "123456789012345678")
    private String idCard;
    @Schema(description = "Address of the student", example = "123 Main St, Springfield")
    private String address;
    @Schema(description = "Class ID the student belongs to", example = "101")
    private Integer clazzId;
    @Schema(description = "Is the student from college", example = "0/1")
    private Integer isCollege;
    @Schema(description = "Degree of the student", example = "1")
    private Integer degree;
    @Schema(description = "Graduation date of the student", example = "2023-06-15")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate graduationDate;
    @Schema(description = "Count of violations by the student", example = "2")
    private Integer violationCount;
    @Schema(description = "Score based on violations", example = "10")
    private Integer violationScore;

    @Schema(description = "Creation time of the student record", example = "2023-10-01T12:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @Schema(description = "Last update time of the student record", example = "2023-10-01T12:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
