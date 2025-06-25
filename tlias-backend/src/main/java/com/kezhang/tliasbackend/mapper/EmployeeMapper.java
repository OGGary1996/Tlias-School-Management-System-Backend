package com.kezhang.tliasbackend.mapper;

import com.kezhang.tliasbackend.dto.EmployeeQueryParam;
import com.kezhang.tliasbackend.dto.EmployeeResponseDTO;
import com.kezhang.tliasbackend.dto.EmployeeUpdateDTO;
import com.kezhang.tliasbackend.entity.Employee;

import java.util.List;

public interface EmployeeMapper {
    /**
     * Select all employees from the database.
     * This method retrieves a list of Employee objects.
     *
     * @return List of Employee objects containing employee details
     */
    List<EmployeeResponseDTO> selectAllEmployees();

//    /**
//     * ！！！传统方式实现的分页查询接口！！！
//     *
//     * Select all employees from the database.
//     * This method retrieves a list of Employee objects.
//     *
//     * // @param offset The offset for pagination, calculated as (page - 1) * pageSize
//     * // @param limit The maximum number of records to retrieve per page
//     * // @return List of EmployeeResponseDTO objects containing employee details
//     */
//    List<EmployeeResponseDTO> selectAllEmployees(@Param("offset") Integer offset,@Param("limit") Integer limit);
//    long countAllEmployees();

//    /*
//    * !!! 使用PageHelper插件实现的分页查询接口 !!!
//    *
//    * Select all employees from the database with pagination.
//    * This method retrieves a list of Employee objects with pagination support.
//    * no parameters are needed as PageHelper will handle pagination automatically.
//    * no need to calculate total records or offset, PageHelper will do it for you.
//    * @return List<EmployeeResponseDTO> containing employee details
//    * */
//    List<EmployeeResponseDTO> selectAllEmployees();

    /*
    * !!! 使用条件查询 + PageHelper插件实现的分页查询接口 !!!
    *
    * Select all employees from the database with pagination and optional filtering.
    * This method retrieves a list of Employee objects with pagination support.
    * @param name Optional name filter for employees
    * @param gender Optional filter for employee
    * @param startDate Optional start date filter for employees
    * @param endDate Optional end date filter for employees
    * @param Page number to retrieve, starting from 1
    * @param pageSize Number of records per page
    * @return List<EmployeeResponseDTO> containing employee details
    * */
//    List<EmployeeResponseDTO> selectEmployeesByCondition(@Param("name") String name,
//                                              @Param("gender") Integer gender,
//                                              @Param("startDate") LocalDate startDate,
//                                              @Param("endDate") LocalDate endDate);

    List<EmployeeResponseDTO> selectEmployeesByCondition(EmployeeQueryParam employeeQueryParam);

    /*
    * Insert a new employee into the database.
    * This method adds a new employee record.
    * @param employee The Employee object containing the details of the employee to be inserted
    * */
    void insertEmployee(Employee employee);

    // 删除员工信息的操作，隶属于删除操作的一部分，另一部分在 EmployeeHistoryMapper 中实现
    /*
    * 1. Get employee image URLs by their IDs.
    * @param ids List of employee IDs to retrieve image URLs for
    * */
    List<String> getEmployeeImageUrlsByIds(List<Integer> ids);
    /*
    * 2. Delete employees by their IDs.
    * @param ids List of employee IDs to be deleted
    * */
    void deleteEmployeeByIds(List<Integer> ids);

    // 查询员工信息（回显），属于修改操作的一部分
    /*
    * Select an employee by their ID for display purposes.
    * @param id The ID of the employee to be selected
    * @return EmployeeUpdateDTO containing employee details with department and position names
    * */
    EmployeeUpdateDTO selectEmployeeById(Integer id);

    // 更新员工信息的操作，隶属于修改操作的一部分,另一部分在 EmployeeHistoryMapper 中实现
    /*
    * Update an existing employee's information and history.
    * @param employeeUpdateCallbackDTO The DTO containing the updated details of the employee
    * */
    Integer updateEmployeeById(Employee employee);

}
