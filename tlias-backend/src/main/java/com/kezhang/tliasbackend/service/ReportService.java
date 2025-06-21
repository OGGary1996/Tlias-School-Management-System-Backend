package com.kezhang.tliasbackend.service;

import com.kezhang.tliasbackend.dto.DepartmentEmployeeCountResponseDTO;
import com.kezhang.tliasbackend.dto.EmployeeGenderDTO;
import com.kezhang.tliasbackend.dto.PositionEmployeeCountResponseDTO;

import java.util.List;

public interface ReportService {
    /*
    * 用于处理前端的部门员工报表请求
    * 报表需要的数据为部门名称列表和对应的员工数量列表
    * */
    DepartmentEmployeeCountResponseDTO getDepartmentEmployeeCount();

    /*
    * 用于处理前端的岗位员工报表请求
    * 报表需要的数据为岗位名称列表和对应的员工数量列表
    * */
    PositionEmployeeCountResponseDTO getPositionEmployeeCount();

    /*
    * 用于处理前端的员工性别的报表请求
    * 报表需要的数据为员工性别和个数组成的对象列表
    * */
    List<EmployeeGenderDTO> getEmployeeGenderCount();
}
