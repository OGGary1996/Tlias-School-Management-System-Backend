package com.kezhang.tliasbackend.service;

import com.kezhang.tliasbackend.dto.*;

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

    /*
    * 用于查询student表中degree字段和人数
    * 报表需要的数据为List<StudentDegreeReportDTO>
    * */
    List<StudentDegreeReportDTO> getStudentDegreeCount();

    /*
    * 用于查询学生班级名称和对应的学生数量
    * 报表需要的数据为List<StudentClazzCountDTO>
    * */
    StudentClazzCountDTO getStudentClazzCount();
}
