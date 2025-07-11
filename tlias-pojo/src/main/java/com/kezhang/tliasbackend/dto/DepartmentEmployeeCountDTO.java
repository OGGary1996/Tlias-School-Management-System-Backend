package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for Department Employee Report XML Data")
public class DepartmentEmployeeCountDTO {
    /*
    * 关于为什么不使用private List<String> departmentNameList;和 private List<Integer> employeeCountList;
    * 如果两个字段都是List字段，则在XML中使用SQL时，MyBatis无法同时通过ResultMap手动映射到两个List字段。
    * MyBatis只能将查询结果映射到一个List字段；后续数据的处理需要在Service层进行。
    * */
    @Schema(description = "Department names")
    private String departmentName;
    @Schema(description = "Employee counts corresponding to each department")
    private Long employeeCount;
}
