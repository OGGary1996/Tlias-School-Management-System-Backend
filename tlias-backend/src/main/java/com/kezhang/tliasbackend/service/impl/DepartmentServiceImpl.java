package com.kezhang.tliasbackend.service.impl;

import com.kezhang.tliasbackend.dto.DepartmentInsertDTO;
import com.kezhang.tliasbackend.dto.DepartmentResponseDTO;
import com.kezhang.tliasbackend.dto.DepartmentUpdateDTO;
import com.kezhang.tliasbackend.entity.Department;
import com.kezhang.tliasbackend.mapper.DepartmentMapper;
import com.kezhang.tliasbackend.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentMapper departmentMapper;
    @Autowired
    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    /*
    * 流程：
    *  1. 从数据库中查询所有部门信息
    *  2. 将查询结果转换为DTO对象
    *  3. 返回DTO对象列表
    * */
    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {
        log.info("Fetching all departments from the database started.");
        List<DepartmentResponseDTO> list = departmentMapper.selectAllDepartments().stream()
                .map(department -> {
                    DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
                    BeanUtils.copyProperties(department, departmentResponseDTO);
                    return departmentResponseDTO;
                }).toList();
        log.info("Fetching all departments from the database completed. Total departments size: {}", list.size());
        return list;
    }

    /*
    * 流程：
    *  1. 从数据库中删除指定ID的部门
    *  2. 返回受影响的行数
    * */
    @Override
    public Integer deleteDepartmentById(Integer id) {
        log.info("Deleting department with ID: {}", id);
        Integer i = departmentMapper.deleteDepartmentById(id);
        log.info("Deletion of department with ID: {} completed. Rows affected: {}", id, i);
        return i;
    }


    /*
    * 流程：
    * 1. 将传入的DTO对象转换为实体对象
    * 2. 调用Mapper层的插入方法，将实体对象插入到数据库中
    * 3. 返回受影响的行数
    * */
    @Override
    public Integer insertDepartment(DepartmentInsertDTO departmentInsertDTO) {
        log.debug("Starting convert DepartmentInsertDTO to Department entity.");
        Department department = new Department();
        BeanUtils.copyProperties(departmentInsertDTO, department);
        log.debug("Conversion completed. Inserting department into the database.");
        log.info("Inserting department with name: {}", department.getName());
        Integer i = departmentMapper.insertDepartment(department);
        log.info("Insertion completed. Rows affected: {}", i);
        return i;
    }

    /*
    * 流程：
    * 1. 从数据库中根据ID查询部门信息
    * 2. 将查询结果转换为DTO对象
    * 3. 返回DTO对象
    * */
    @Override
    public DepartmentResponseDTO getDepartmentById(Integer id) {
        log.info("Retrieved department with ID: {}", id);
        Department department  = departmentMapper.selectDepartmentById(id);
        log.info("getDepartmentById completed. Department details: {}", department);

        log.debug("Starting convert Department entity to DepartmentResponseDTO.");
        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        BeanUtils.copyProperties(department, departmentResponseDTO);
        log.debug("Conversion completed. Returning DepartmentResponseDTO.");
        return departmentResponseDTO;
    }

    /*
    * 流程
    * 1. 将传入的DTO对象转换为实体对象
    * 2. 调用Mapper层的更新方法，将实体对象更新到数据库中
    * 3. 返回受影响的行数
    * */
    @Override
    public Integer updateDepartmentById(DepartmentUpdateDTO departmentUpdateDTO) {
        log.debug("Starting convert DepartmentUpdateDTO to Department entity.");
        Department department = new Department();
        BeanUtils.copyProperties(departmentUpdateDTO, department);
        log.debug("Conversion completed. Updating department in the database.");

        log.info("Updating department with ID: {}", department.getId());
        Integer i = departmentMapper.updateDepartmentById(department);
        log.info("Update completed. Rows affected: {}", i);
        return i;
    }
}
