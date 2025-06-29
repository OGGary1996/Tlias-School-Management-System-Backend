package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for employee login response")
public class EmployeeLoginResponseDTO {
    @Schema(description = "Employee's ID", example = "1")
    private Integer id;
    @Schema(description = "Employee's name", example = "John Doe")
    private String name;
    @Schema(description = "Employee's username", example = "johnDoe")
    private String username;
    @Schema(description = "Login token for the employee", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
}
