package com.kezhang.tliasbackend.service.impl;

import com.kezhang.tliasbackend.dto.DepartmentEmployeeCountDTO;
import com.kezhang.tliasbackend.dto.DepartmentEmployeeCountResponseDTO;
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
}
