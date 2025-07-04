package com.kezhang.tliasbackend.controller;

import com.kezhang.tliasbackend.annotation.OperationLog;
import com.kezhang.tliasbackend.dto.SubjectInsertDTO;
import com.kezhang.tliasbackend.dto.SubjectResponseDTO;
import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.dto.SubjectUpdateDTO;
import com.kezhang.tliasbackend.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/subjects")
@Tag(name = "SubjectController", description = "Controller for managing subjects")
public class SubjectController {
    private final SubjectService subjectService;
    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    @Operation(summary = "List all subjects", description = "Retrieve a list of all subjects in the system")
    public Result<?> listAllSubjects(){
        log.info("Fetching all subjects from the service started.");
        List<SubjectResponseDTO> list = subjectService.getAllSubjects();
        log.info("Fetching all subjects from the service completed. Total subjects size: {}", list.size());
        return Result.success(list);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a subject", description = "Delete a subject by its ID")
    @OperationLog
    public Result<?> deleteSubject(@PathVariable Integer id){
        log.info("Deleting subject with ID: {}", id);
        subjectService.deleteSubjectById(id);
        log.info("Deletion of subject with ID: {} completed.", id);
        return Result.success(null);
    }

    @PostMapping
    @Operation(summary = "Create a new subject", description = "Insert a new subject into the system")
    @OperationLog
    public Result<?> createSubject(@RequestBody @Valid SubjectInsertDTO subjectInsertDTO){
        log.info("Creating a new subject with details: {}", subjectInsertDTO);
        subjectService.insertSubject(subjectInsertDTO);
        log.info("Creation of new subject completed." );
        return Result.success(null);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get subject by ID", description = "Retrieve a subject by its ID")
    public Result<?> getSubject(@PathVariable Integer id){
        log.info("Fetching subject with ID: {}", id);
        SubjectResponseDTO subjectResponseDTO = subjectService.getSubjectById(id);
        log.info("Fetching subject with ID: {} completed. Subject details: {}", id, subjectResponseDTO);
        return Result.success(subjectResponseDTO);
    }

    @PutMapping
    @Operation(summary = "Update a subject", description = "Update an existing subject's details")
    @OperationLog
    public Result<?> updateSubject(@RequestBody @Valid SubjectUpdateDTO subjectUpdateDTO){
        log.info("Updating subject with details: {}", subjectUpdateDTO);
        subjectService.updateSubjectById(subjectUpdateDTO);
        log.info("Update of subject completed.");
        return Result.success(null);
    }
} 