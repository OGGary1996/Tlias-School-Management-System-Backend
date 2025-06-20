package com.kezhang.tliasbackend.mapper;

import com.kezhang.tliasbackend.dto.DepartmentEmployeeCountDTO;

import java.util.List;

public interface ReportMapper {
    /*
    * 查询部门的名称和对应的员工数量
    * */
    List<DepartmentEmployeeCountDTO> getDepartmentEmployeeCount();
}
