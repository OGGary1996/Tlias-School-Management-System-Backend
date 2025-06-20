package com.kezhang.tliasbackend.service;

import com.kezhang.tliasbackend.dto.DepartmentEmployeeCountResponseDTO;

public interface ReportService {
    /*
    * 用于处理前端的部门员工报表请求
    * 报表需要的数据为部门名称列表和对应的员工数量列表
    * */
    DepartmentEmployeeCountResponseDTO getDepartmentEmployeeCount();
}
