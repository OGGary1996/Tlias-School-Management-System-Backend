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
@Schema(description = "Insert DTO for Student entity")
public class StudentInsertDTO {
    @Schema(description = "Name of the student", example = "John Doe")
    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null")
    private String name;
    @Schema(description = "Account number of student", example = "123456789")
    @NotBlank(message = "Account number cannot be blank")
    @NotNull(message = "Account number cannot be null")
    private String accountNumber;
    @Schema(description = "Gender of the student", example = "1")
    @NotNull(message = "Gender cannot be null")
    private Integer gender;
    @Schema(description = "Phone number of the student", example = "123-456-7890")
    @NotBlank(message = "Phone number cannot be blank")
    @NotNull(message = "Phone number cannot be null")
    private String phone;
    @Schema(description = "ID card number of the student", example = "123456789012345678")
    @NotBlank(message = "ID card cannot be blank")
    @NotNull(message = "ID card cannot be null")
    private String idCard;
    @Schema(description = "Address of the student", example = "123 Main St, Springfield")
    @NotBlank(message = "Address cannot be blank")
    @NotNull(message = "Address cannot be null")
    private String address;
    @Schema(description = "Class ID the student belongs to", example = "101")
    @NotNull(message = "Class ID cannot be null")
    private Integer clazzId;
    @Schema(description = "Is the student from college", example = "0/1")
    @NotNull(message = "College status cannot be null")
    private Integer isCollege;
    @Schema(description = "Degree of the student", example = "1")
    @NotNull(message = "Degree cannot be null")
    private Integer degree;
    @Schema(description = "Graduation date of the student", example = "2023-06-15")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate graduationDate;
}
