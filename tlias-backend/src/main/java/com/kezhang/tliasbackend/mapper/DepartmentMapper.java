package com.kezhang.tliasbackend.mapper;


import com.kezhang.tliasbackend.entity.Department;

import java.util.List;

public interface DepartmentMapper {
    /**
     * select all departments from the database.
     *
     * @return the List of departments after insertion
     */
    List<Department> selectAllDepartments();

    /*
    * delete a department by id.
    *
    * @param id the id of the department to be deleted
    *
    * @return the number of rows affected by the deletion
    * */
    Integer deleteDepartmentById(Integer id);

    /*
    * Insert a new department into the database.
    *
    * @param department the department to be inserted
    *
    * @return the number of rows affected by the insertion
    * */
    Integer insertDepartment(Department department);


    /*
    * Select a department by id.
    *
    * @param id the id of the department to be selected
    *
    * @return the Department entity containing department details
    *
    * */
    Department selectDepartmentById(Integer id);


    /*
    * Update a department by id.
    *
    * @param department the department entity containing updated details
    *
    * @return the number of rows affected by the update
    * */
    Integer updateDepartmentById(Department department);
}
