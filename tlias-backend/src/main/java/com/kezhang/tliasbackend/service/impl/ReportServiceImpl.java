package com.kezhang.tliasbackend.service.impl;

import com.kezhang.tliasbackend.dto.*;
import com.kezhang.tliasbackend.mapper.ReportMapper;
import com.kezhang.tliasbackend.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<Integer> employeeCountList = departmentEmployeeCountList.stream().map(item -> item.getEmployeeCount()).toList();
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
        List<Integer> employeeCountList = positionEmployeeCountList.stream().map(item -> item.getEmployeeCount()).toList();
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
}
