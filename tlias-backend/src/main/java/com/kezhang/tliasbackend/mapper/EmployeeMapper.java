package com.kezhang.tliasbackend.mapper;

import com.github.pagehelper.Page;
import com.kezhang.tliasbackend.dto.EmployeeQueryDTO;
import com.kezhang.tliasbackend.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeMapper {
//    /**
//     * ！！！传统方式实现的分页查询接口！！！
//     *
//     * Select all employees from the database.
//     * This method retrieves a list of Employee objects.
//     *
//     * // @param offset The offset for pagination, calculated as (page - 1) * pageSize
//     * // @param limit The maximum number of records to retrieve per page
//     * // @return List of Employee objects containing employee details
//     */
//    List<Employee> selectAllEmployees(@Param("offset") Integer offset,@Param("limit") Integer limit);
//    long countAllEmployees();

//    /*
//    * !!! 使用PageHelper插件实现的分页查询接口 !!!
//    *
//    * Select all employees from the database with pagination.
//    * This method retrieves a list of Employee objects with pagination support.
//    * no parameters are needed as PageHelper will handle pagination automatically.
//    * no need to calculate total records or offset, PageHelper will do it for you.
//    * @return Page<Employee> containing employee details
//    * */
//    List<Employee> selectAllEmployees();

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
    * @return List<Employee> containing employee details
    * */
//    List<Employee> selectEmployeesByCondition(@Param("name") String name,
//                                              @Param("gender") Integer gender,
//                                              @Param("startDate") LocalDate startDate,
//                                              @Param("endDate") LocalDate endDate);

    List<Employee> selectEmployeesByCondition(EmployeeQueryDTO employeeQueryDTO);
}
