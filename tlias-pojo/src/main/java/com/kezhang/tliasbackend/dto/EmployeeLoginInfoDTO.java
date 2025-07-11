package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for employee login information")
public class EmployeeLoginInfoDTO {
    @Schema(description = "Employee's username", example = "johnDoe")
    private String username;
    @Schema(description = "Employee's password", example = "securePassword123")
    private String password;
}
