package com.kezhang.tliasbackend.service.impl;

import com.kezhang.tliasbackend.constant.StudentDegreeEnum;
import com.kezhang.tliasbackend.dto.*;
import com.kezhang.tliasbackend.mapper.ReportMapper;
import com.kezhang.tliasbackend.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {
    private final ReportMapper reportMapper;
    @Autowired
    public ReportServiceImpl(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    /*
    * 说明：
    *  1.由于MyBatis的resultMap无法手动映射到多个List字段，所以此DTO设计字段为两个基本类型字段；而不是两个List字段。
    *  2.所以，在Service层中，需要进行一次数据转换，将查询结果转换为前端需要的DTO类型对象。
    * 流程：
    * 1. 首先获取到DepartmentEmployeeCountDTO列表
    * 2. 将查询结果转换为前端需要的DTO类型对象 DepartmentEmployeeCountResponseDTOs
    * */
    @Override
    public DepartmentEmployeeCountResponseDTO getDepartmentEmployeeCount() {
        // 1. 首先获取到DepartmentEmployeeCountDTO列表
        log.info("Fetching department employee count data.");
        List<DepartmentEmployeeCountDTO> departmentEmployeeCountList = reportMapper.getDepartmentEmployeeCount();
        log.info("Fetched department employee count data: {}", departmentEmployeeCountList);

        // 2. 将查询结果转换为前端需要的DTO类型对象 DepartmentEmployeeCountResponseDTOs
        log.info("Converting fetched data to DepartmentEmployeeCountDTO.");
        List<String> departmentNameList = departmentEmployeeCountList.stream().map(item -> item.getDepartmentName()).toList();
        log.info("Converted department names: {}", departmentNameList);
        List<Long> employeeCountList = departmentEmployeeCountList.stream().map(item -> item.getEmployeeCount()).toList();
        log.info("Converted employee counts: {}", employeeCountList);
        DepartmentEmployeeCountResponseDTO departmentEmployeeCountResponseDTO = new DepartmentEmployeeCountResponseDTO();
        departmentEmployeeCountResponseDTO.setDepartmentNameList(departmentNameList);
        departmentEmployeeCountResponseDTO.setEmployeeCountList(employeeCountList);
        log.info("Final DepartmentEmployeeCountResponseDTO: {}", departmentEmployeeCountResponseDTO);

        return departmentEmployeeCountResponseDTO;
    }

    /*
    * 流程：
    *  1. 首先获取到PositionEmployeeCountDTO组成的List
    *  2. 将查询结果转换为前端需要的DTO类型对象 PositionEmployeeCountResponseDTOs
    * */
    @Override
    public PositionEmployeeCountResponseDTO getPositionEmployeeCount() {
        log.info("Fetching position employee count data.");
        // 1. 首先获取到PositionEmployeeCountDTO组成的List
        List<PositionEmployeeCountDTO> positionEmployeeCountList = reportMapper.getPositionEmployeeCount();
        log.info("Fetched position employee count data: {}", positionEmployeeCountList);

        // 2. 将查询结果转换为前端需要的DTO类型对象 PositionEmployeeCountResponseDTOs
        log.info("Converting fetched data to PositionEmployeeCountDTO.");
        List<String> positionNameList = positionEmployeeCountList.stream().map(item -> item.getPositionName()).toList();
        log.info("Converted position names: {}", positionNameList);
        List<Long> employeeCountList = positionEmployeeCountList.stream().map(item -> item.getEmployeeCount()).toList();
        log.info("Converted employee counts: {}", employeeCountList);
        PositionEmployeeCountResponseDTO positionEmployeeCountResponseDTO = new PositionEmployeeCountResponseDTO();
        positionEmployeeCountResponseDTO.setPositionNameList(positionNameList);
        positionEmployeeCountResponseDTO.setEmployeeCountList(employeeCountList);
        log.info("Final PositionEmployeeCountResponseDTO: {}", positionEmployeeCountResponseDTO);

        return positionEmployeeCountResponseDTO;
    }

    /*
    * 流程：
    *  1. 获取到员工性别和个数组成的对象列表
    *  2. 返回结果
    * */
    @Override
    public List<EmployeeGenderDTO> getEmployeeGenderCount() {
        log.info("Fetching DTO from ReportMapper");
        List<EmployeeGenderDTO> employeeGenderCountList = reportMapper.getEmployeeGenderCount();
        log.info("Fetched DTO List: {}", employeeGenderCountList);
        return employeeGenderCountList;
    }

    /*
    * 流程：
    *  1. 获取到mapper传递回的Map<String, Object>列表
    *  2. 将Map转换为StudentDegreeReportDTO列表
    *  3. 转换过程中需要用到StudentDegreeEnum的getLabelByCode方法来获取学位名称
    *  3. 返回结果
    * */
    @Override
    public List<StudentDegreeReportDTO> getStudentDegreeCount() {
        log.info("Fetching student degree count data from ReportMapper.");
        List<Map<String, Object>> studentDegreeCount = reportMapper.getStudentDegreeCount();
        log.info("Fetched student degree count data: {}", studentDegreeCount);

        // 将Map转换为StudentDegreeReportDTO列表
        List<StudentDegreeReportDTO> studentDegreeReportDTOList = new ArrayList<>();
        studentDegreeCount.forEach(item -> {
            // 首先获取到Map中的key(degree) -> value(degreeCode) 和 key(count) -> value(count)
            Integer degreeCode = (Integer) item.get("degree");
            Long count = (Long) item.get("count");
            // 使用StudentDegreeEnum的getLabelByCode方法获取degreeCode对应的学位名称
            String degreeLabel = StudentDegreeEnum.getLabelByCode(degreeCode);
            // 将degreeLabel和count封装到StudentDegreeReportDTO中
            StudentDegreeReportDTO studentDegreeReportDTO = new StudentDegreeReportDTO(degreeLabel, count);
            studentDegreeReportDTOList.add(studentDegreeReportDTO);
            log.info("Converted to StudentDegreeReportDTO: {}", studentDegreeReportDTO);
        });
        log.info("Final StudentDegreeReportDTO List: {}", studentDegreeReportDTOList);

        return studentDegreeReportDTOList;
    }

    /*
    * 流程：
    *  1. 获取到mapper传递回的Map<String, Object>列表
    *  2. 遍历List<Map<String, Object>>，取出每个Map中的key(clazzName) -> value(clazzName) 和 key(count) -> value(count)
    *  3. 将clazzName和count组成List，然后封装到StudentClazzCountDTO中
    * */
    @Override
    public StudentClazzCountDTO getStudentClazzCount() {
        log.info("Fetching student class count data from ReportMapper.");
        List<Map<String, Object>> studentClazzCountMaps = reportMapper.getStudentClazzCount();
        log.info("Fetched student class count data: {}", studentClazzCountMaps);

        // 遍历List<Map<String, Object>>，取出每个Map中的key(clazzName) -> value(clazzName) 和 key(count) -> value(count)
        // 并且分别封装为List
        List<String> clazzNameList = new ArrayList<>();
        List<Long> countList = new ArrayList<>();
        studentClazzCountMaps.forEach(item -> {
            String clazzName = (String)item.get("clazzName"); // 获取班级名称
            Long count = (Long)item.get("count"); // 获取学生数量
            clazzNameList.add(clazzName); // 添加到班级名称列表
            countList.add(count); // 添加到学生数量列表
        });
        StudentClazzCountDTO studentClazzCountDTO = new StudentClazzCountDTO(clazzNameList, countList);
        log.info("Final StudentClazzCountDTO: {}", studentClazzCountDTO);

        return studentClazzCountDTO;
    }
}
