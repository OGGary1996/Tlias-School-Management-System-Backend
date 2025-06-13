package com.kezhang.tliasbackend.controller;

import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.dto.EmployeeQueryDTO;
import com.kezhang.tliasbackend.dto.EmployeeResponseDTO;
import com.kezhang.tliasbackend.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Slf4j
@RestController
@RequestMapping("/employees")
@Tag(name = "EmployeeController", description = "Controller for managing employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    /**
//     * 普通分页查询接口
//     * @param page 当前页码，从1开始
//     * @param pageSize 每页显示条数
//     * @return 分页数据 + 总条数
//     */
//    @Operation(summary = "List all employees", description = "Retrieve a list of all employees with their department names and job titles")
//    @GetMapping
//    public Result<?> listAllEmployees(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
//        log.info("Fetching all employees from the service started.");
//        PageResult<EmployeeResponseDTO> pageResult = employeeService.selectAllEmployees(page, pageSize);
//        log.info("Fetched all employees from the service completed.");
//
//        return Result.success(pageResult);
//    }

    /*
    * 分页查询 + 条件查询接口
    * @param name 员工姓名
    * @param gender 员工性别
    * @param startDate 员工入职开始日期
    * @param endDate 员工入职结束日期
    * @param page 当前页码，从1开始
    * @param pageSize 每页显示条数
    * @return 分页数据 + 总条数
    */
//    @Operation(summary = "List employees by condition", description = "Retrieve a list of employees based on optional filters such as name")
//    @GetMapping
//    public Result<?> listEmployeesByCondition(@RequestParam(value="name",required = false) String name,
//                                              @RequestParam(value = "gender",required = false) Integer gender,
//                                              @RequestParam(value = "startDate",required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDate,
//                                              @RequestParam(value = "endDate",required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate,
//                                              @RequestParam(value = "page",defaultValue = "1") Integer page,
//                                              @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
//        log.info("Fetching employees by condition started. Name: {},Gender: {}, StartDate: {}, EndDate: {}, Page: {}, PageSize: {}",name,gender,startDate,endDate,page,pageSize);
//        PageResult<EmployeeResponseDTO> employeeResponseDTOPageResult = employeeService.selectEmployeesByCondition(name, gender, startDate, endDate, page, pageSize);
//        log.info("Fetched employees by condition completed. Total records: {}, Records size: {}", employeeResponseDTOPageResult.getTotal(), employeeResponseDTOPageResult.getRecords().size());
//
//        return Result.success(employeeResponseDTOPageResult);
//    }
    @Operation(summary = "List employees by condition", description = "Retrieve a list of employees based on optional filters such as name")
    @GetMapping
    public Result<?> listEmployeesByCondition(EmployeeQueryDTO employeeQueryDTO) {
        log.info("Fetching employees by condition started. DTO: {}",employeeQueryDTO);
        PageResult<EmployeeResponseDTO> employeeResponseDTOPageResult = employeeService.selectEmployeesByCondition(employeeQueryDTO);
        log.info("Fetched employees by condition completed. Total records: {}, Records size: {}", employeeResponseDTOPageResult.getTotal(), employeeResponseDTOPageResult.getRecords().size());

        return Result.success(employeeResponseDTOPageResult);
    }
}
