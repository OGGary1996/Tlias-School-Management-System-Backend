package com.kezhang.tliasbackend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.dto.EmployeeQueryDTO;
import com.kezhang.tliasbackend.dto.EmployeeResponseDTO;
import com.kezhang.tliasbackend.entity.Employee;
import com.kezhang.tliasbackend.mapper.EmployeeMapper;
import com.kezhang.tliasbackend.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }


//    /*
//    * 流程：
//    * 1. 查询所有员工信息，使用多表查询
//    * 2. 将查询结果转换为 EmployeeResponseDTO 对象
//    * 3. 将 EmployeeResponseDTO 对象列表转换为 PageResult 对象
//    * 4. 返回 PageResult 对象
//    * */
//    @Override
//    public PageResult<EmployeeResponseDTO> selectAllEmployees(Integer page, Integer limit) {
//        log.info("Fetching all employees with their department names and job titles started.");
//        int offset = (page - 1) * limit;
//        List<Employee> employeeList = employeeMapper.selectAllEmployees(offset, limit);
//        log.info("Fetched {} employees from the database.", employeeList.size());
//
//        log.info("Converting Employee entities to EmployeeResponseDTOs then convert to PageResult.");
//        List<EmployeeResponseDTO> employeeResponseDTOS = employeeList.stream().map(emp -> {
//            EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
//            BeanUtils.copyProperties(emp, employeeResponseDTO);
//            return employeeResponseDTO;
//        }).toList();
//        log.info("Converted {} Employee entities to EmployeeResponseDTOs completed.", employeeResponseDTOS.size());
//
//        long total = employeeMapper.countAllEmployees();
//        log.info("Total number of employees in the database: {}", total);
//        PageResult pageResult = PageResult.<EmployeeResponseDTO>builder()
//                .total(total)
//                .records(employeeResponseDTOS)
//                .build();
//        log.info("Returning PageResult with total: {} and records size: {}", pageResult.getTotal(), pageResult.getRecords().size());
//
//        return pageResult;

//    /*
//    * !! 基于 PageHelper 插件的分页接口 !!
//    * 流程：
//    * 1. 查询所有员工信息，使用多表查询
//    * 2. 使用 PageHelper 插件自动处理分页
//    * 3. 将查询结果转换为 EmployeeResponseDTO 对象
//    * 4. 将 EmployeeResponseDTO 对象列表转换为 PageResult 对象
//    * 5. 返回 PageResult 对象
//    * */
//    @Override
//    public PageResult<EmployeeResponseDTO> selectAllEmployees(Integer page,Integer pageSize){
//        log.info("Fetching all employees with their department names and job titles started.");
//        PageHelper.startPage(page, pageSize); // 使用 PageHelper 插件自动处理分页，注意，这两行代码必须在查询之前调用
//        List<Employee> employeeList = employeeMapper.selectAllEmployees(); // 执行原始全部查询操作
//        log.info("Fetched {} employees from the database.", employeeList.size());
//
//        log.info("Converting Employee entities to EmployeeResponseDTOs then convert to PageResult.");
//        List<EmployeeResponseDTO> employeeResponseDTOS = employeeList.stream().map(emp -> {
//            EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
//            BeanUtils.copyProperties(emp, employeeResponseDTO);
//            return employeeResponseDTO;
//        }).toList(); // 使用 PageHelper 的 toPage 方法将 List 转换为 Page 对象
//        log.info("Converted {} Employee entities to EmployeeResponseDTOs completed.", employeeResponseDTOS.size());
//
//        // 使用 PageHelper 插件自动处理分页后，PageHelper 会将查询结果转换为 Page 对象
//        PageInfo<EmployeeResponseDTO> pageInfo = new PageInfo<>(employeeResponseDTOS); // 将 List 转换为 Page 对象
//
//        return PageResult.<EmployeeResponseDTO>builder()
//                .total(pageInfo.getTotal())
//                .records(pageInfo.getList()) // 获取分页后的记录列表
//                .build();
//}

    /*
    * !! 基于 条件查询 + PageHelper 插件的分页接口 !!
    * 流程：
    * 1. 根据条件查询员工信息，使用多表查询
    * 2. 使用 PageHelper 插件自动处理分页
    * 3. 将查询结果转换为 EmployeeResponseDTO 对象
    * 4. 将 EmployeeResponseDTO 对象列表转换为 PageResult 对象
    * 5. 返回 PageResult 对象
    * */
//    @Override
//    public PageResult<EmployeeResponseDTO> selectEmployeesByCondition(String name, Integer gender,
//                                                                      LocalDate startDate, LocalDate endDate,
//                                                                      Integer page, Integer pageSize) {
//        log.info("Fetching employees by condition started.");
//        PageHelper.startPage(page, pageSize); // 使用 PageHelper 插件自动处理分页，注意，这两行代码必须在查询之前调用
//        List<Employee> employeeList = employeeMapper.selectEmployeesByCondition(name, gender, startDate, endDate);
//        log.info("Fetched {} employees from the database based on the given conditions.", employeeList.size());
//
//        log.info("Converting Employee entities to EmployeeResponseDTOs then convert to PageResult.");
//        List<EmployeeResponseDTO> employeeResponseDTOS = employeeList.stream().map(emp -> {
//            EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
//            BeanUtils.copyProperties(emp, employeeResponseDTO);
//            return employeeResponseDTO;
//        }).toList();
//        log.info("Converted {} Employee entities to EmployeeResponseDTOs completed.", employeeResponseDTOS.size());
//
//        log.info("Converting EmployeeResponseDTOs to PageInfo.");
//        PageInfo<EmployeeResponseDTO> pageInfo = new PageInfo<>(employeeResponseDTOS); // 将 List 转换为 Page 对象
//        log.info("Returning PageResult with total: {} and records size: {}", pageInfo.getTotal(), pageInfo.getList().size());
//        return PageResult.<EmployeeResponseDTO>builder()
//                .total(pageInfo.getTotal())
//                .records(pageInfo.getList()) // 获取分页后的记录列表
//                .build();
//    }
    @Override
        public PageResult<EmployeeResponseDTO> selectEmployeesByCondition(EmployeeQueryDTO employeeQueryDTO) {
        log.info("Fetching employees by condition started.");
        PageHelper.startPage(employeeQueryDTO.getPage(), employeeQueryDTO.getPageSize()); // 使用 PageHelper 插件自动处理分页，注意，这两行代码必须在查询之前调用
        List<Employee> employeeList = employeeMapper.selectEmployeesByCondition(employeeQueryDTO);
        log.info("Fetched {} employees from the database based on the given conditions.", employeeList.size());

        log.info("Converting Employee entities to EmployeeResponseDTOs then convert to PageResult.");
        List<EmployeeResponseDTO> employeeResponseDTOS = employeeList.stream().map(emp -> {
            EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(emp, employeeResponseDTO);
            return employeeResponseDTO;
        }).toList();
        log.info("Converted {} Employee entities to EmployeeResponseDTOs completed.", employeeResponseDTOS.size());

        log.info("Converting EmployeeResponseDTOs to PageInfo.");
        PageInfo<EmployeeResponseDTO> pageInfo = new PageInfo<>(employeeResponseDTOS); // 将 List 转换为 Page 对象
        log.info("Returning PageResult with total: {} and records size: {}", pageInfo.getTotal(), pageInfo.getList().size());
        return PageResult.<EmployeeResponseDTO>builder()
                .total(pageInfo.getTotal())
                .records(pageInfo.getList()) // 获取分页后的记录列表
                .build();
    }
}
