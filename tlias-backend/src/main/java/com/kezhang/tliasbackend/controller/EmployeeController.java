package com.kezhang.tliasbackend.controller;

import com.aliyuncs.exceptions.ClientException;
import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.dto.EmployeeInsertDTO;
import com.kezhang.tliasbackend.dto.EmployeeQueryParam;
import com.kezhang.tliasbackend.dto.EmployeeResponseDTO;
import com.kezhang.tliasbackend.dto.EmployeeUpdateCallbackDTO;
import com.kezhang.tliasbackend.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public Result<?> listEmployeesByCondition(EmployeeQueryParam employeeQueryParam) {
        log.info("Fetching employees by condition started. DTO: {}",employeeQueryParam);
        PageResult<EmployeeResponseDTO> employeeResponseDTOPageResult = employeeService.selectEmployeesByCondition(employeeQueryParam);
        log.info("Fetched employees by condition completed. Total records: {}, Records size: {}", employeeResponseDTOPageResult.getTotal(), employeeResponseDTOPageResult.getRecords().size());

        return Result.success(employeeResponseDTOPageResult);
    }

    /**
     * 插入员工信息接口
     *
     * @param employeeInsertDTO 员工信息 DTO，注意DTO中包含了员工历史信息的子 DTO
     * @return 成功或失败的结果
     */
    @Operation(summary = "Insert employee", description = "Insert a new employee into the database")
    @PostMapping
    public Result<?> createEmployee(@RequestBody EmployeeInsertDTO employeeInsertDTO){
       log.info("Inserting new employee started. DTO: {}", employeeInsertDTO);
       employeeService.insertEmployee(employeeInsertDTO);
       log.info("Inserting new employee completed.");

       return Result.success(null);
    }

    /*
    * 删除员工信息接口（员工信息 + 员工过往经历 + OSS头像删除）
    * @param id 员工IDList
    * @return 成功或失败的结果
    * */
    @Operation(summary = "Delete employee", description = "Delete an employee by their ID")
    @DeleteMapping
    public Result<?> deleteEmployee(@RequestParam("ids") List<Integer> ids) throws ClientException {
        log.info("Deleting employee started. IDs: {}", ids);
        employeeService.deleteEmployee(ids);
        log.info("Deleting employee completed.");

        return Result.success(null);
    }

    /*
    * 修改用户时的回显接口
    * @param id 员工ID
    * @return Result
    * */
    @Operation(summary = "Get employee by ID", description = "Retrieve an employee's details by their ID")
    @GetMapping("/{id}")
    public Result<?> getEmployeeById(@PathVariable("id") Integer id){
        log.info("Fetching employee by ID: {}", id);
        EmployeeUpdateCallbackDTO employeeUpdateCallbackDTO = employeeService.selectEmployeeById(id);
        log.info("Fetched employee by ID completed. Employee details: {}", employeeUpdateCallbackDTO);
        return Result.success(employeeUpdateCallbackDTO);
    }
    /*
    * 修改员工时的修改接口（员工信息 + 员工过往经历修改）
    * @param employeeUpdateCallbackDTO 员工信息 DTO，注意DTO中包含了员工历史信息的子 DTO
    * @return 成功或失败的结果
    * */
    @Operation(summary = "Update employee", description = "Update an existing employee's details")
    @PutMapping
    public Result<?> updateEmployee(@RequestBody EmployeeUpdateCallbackDTO employeeUpdateCallbackDTO){
        log.info("Updating employee started. DTO: {}", employeeUpdateCallbackDTO);
        employeeService.updateEmployee(employeeUpdateCallbackDTO);
        log.info("Updating employee completed.");
        return Result.success(null);
    }
}
