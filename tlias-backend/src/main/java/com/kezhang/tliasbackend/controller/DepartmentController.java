package com.kezhang.tliasbackend.controller;

import com.kezhang.tliasbackend.dto.DepartmentInsertDTO;
import com.kezhang.tliasbackend.dto.DepartmentResponseDTO;
import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.dto.DepartmentUpdateDTO;
import com.kezhang.tliasbackend.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/departments")
@Tag(name = "DepartmentController", description = "Controller for managing departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    @Operation(summary = "List all departments", description = "Retrieve a list of all departments in the system")
    public Result<?> listAllDepartments(){
        log.info("Fetching all departments from the service started.");
        List<DepartmentResponseDTO> list = departmentService.getAllDepartments();
        log.info("Fetching all departments from the service completed. Total departments size: {}", list.size());
        return Result.success(list);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a department", description = "Delete a department by its ID")
    public Result<?> deleteDepartment(@PathVariable Integer id){
        log.info("Deleting department with ID: {}", id);
        departmentService.deleteDepartmentById(id);
        log.info("Deletion of department with ID: {} completed.", id);
        return Result.success(null);
    }

    @PostMapping
    @Operation(summary = "Create a new department", description = "Insert a new department into the system")
    public Result<?> createDepartment(@RequestBody @Valid DepartmentInsertDTO departmentInsertDTO){
        log.info("Creating a new department with details: {}", departmentInsertDTO);
        departmentService.insertDepartment(departmentInsertDTO);
        log.info("Creation of new department completed." );
        return Result.success(null);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get department by ID", description = "Retrieve a department by its ID")
    public Result<?> getDepartment(@PathVariable Integer id){
        log.info("Fetching department with ID: {}", id);
        DepartmentResponseDTO departmentResponseDTO = departmentService.getDepartmentById(id);
        log.info("Fetching department with ID: {} completed. Department details: {}", id, departmentResponseDTO);
        return Result.success(departmentResponseDTO);
    }

    @PutMapping
    @Operation(summary = "Update a department", description = "Update an existing department's details")
    public Result<?> updateDepartment(@RequestBody @Valid DepartmentUpdateDTO departmentUpdateDTO){
        log.info("Updating department with details: {}", departmentUpdateDTO);
        departmentService.updateDepartmentById(departmentUpdateDTO);
        log.info("Update of department completed.");
        return Result.success(null);
    }
}
