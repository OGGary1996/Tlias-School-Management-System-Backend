package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Student Class Count Data Transfer Object")
public class StudentClazzCountDTO {
    @Schema(description = "List of class names", example = "[\"Class A\",\"Class B\"]")
    private List<String> clazzNameList; // 班级名称列表
    @Schema(description = "List of student counts corresponding to each class", example = "[30, 25]")
    private List<Long> countList; // 对应的学生数量列表
}
