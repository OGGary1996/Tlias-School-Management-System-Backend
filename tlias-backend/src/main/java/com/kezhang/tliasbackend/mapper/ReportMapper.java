package com.kezhang.tliasbackend.mapper;

import com.kezhang.tliasbackend.dto.DepartmentEmployeeCountDTO;
import com.kezhang.tliasbackend.dto.EmployeeGenderDTO;
import com.kezhang.tliasbackend.dto.PositionEmployeeCountDTO;
import com.kezhang.tliasbackend.dto.PositionEmployeeCountResponseDTO;

import java.util.List;
import java.util.Map;

public interface ReportMapper {
    /*
    * 查询部门的名称和对应的员工数量
    * */
    List<DepartmentEmployeeCountDTO> getDepartmentEmployeeCount();

    /*
    * 查询岗位的名称和对应的员工数量
    * */
    List<PositionEmployeeCountDTO> getPositionEmployeeCount();

    /*
    * 查询员工性别和对应的数量
    * */
    List<EmployeeGenderDTO> getEmployeeGenderCount();

    /*
    * 查询student表中degree字段和人数
    * */
    List<Map<String,Object>> getStudentDegreeCount();

    /*
    * 查询学生班级名称和对应的学生数量
    * */
    List<Map<String,Object>> getStudentClazzCount();
}
