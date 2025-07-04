package com.kezhang.tliasbackend.controller;

import com.kezhang.tliasbackend.annotation.OperationLog;
import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.dto.StudentInsertDTO;
import com.kezhang.tliasbackend.dto.StudentQueryParam;
import com.kezhang.tliasbackend.dto.StudentResponseDTO;
import com.kezhang.tliasbackend.dto.StudentUpdateDTO;
import com.kezhang.tliasbackend.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
@Tag(name = "Student Management", description = "APIs for managing students")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /*
    * Select students by condition
    * @param queryParam Query parameters for filtering students
    * @return Result containing a paginated list of students matching the query parameters
    * */
    @Operation(summary = "Get students by condition", description = "Retrieve a list of students based on specified query parameters")
    @GetMapping
    public Result<?> getStudentsByCondition(StudentQueryParam queryParam) {
        log.info("Received request to get students by condition: {}", queryParam);
        PageResult<StudentResponseDTO> studentList = studentService.getStudentByCondition(queryParam);
        log.info("Returning student list with total: {}, records: {}", studentList.getTotal(), studentList.getRecords().size());
        return Result.success(studentList);
    }

    /*
    * Insert a new student
    * @param studentInsertDTO Data Transfer Object containing student details to be inserted
    * @return Result indicating the success or failure of the insertion operation
    * */
    @Operation(summary = "Insert a new student", description = "Add a new student to the system")
    @PostMapping
    @OperationLog
    public Result<?> createStudent(@RequestBody StudentInsertDTO studentInsertDTO){
        log.info("Received request to create student: {}", studentInsertDTO);
        studentService.insertStudent(studentInsertDTO);
        log.info("Student created successfully: {}", studentInsertDTO);
        return Result.success("Student created successfully");
    }

    /*
    * Delete a student by Id
    * @param id The List of the student ID to be deleted
    * */
    @Operation(summary = "Delete a student by ID", description = "Remove a student from the system using their ID")
    @DeleteMapping("/{ids}")
    @OperationLog
    public Result<?> deleteStudentById(@PathVariable("ids") List<Integer> ids){
        log.info("Received request to delete students with IDs: {}", ids);
        studentService.deleteStudentById(ids);
        log.info("Students deleted successfully with IDs: {}", ids);
        return Result.success(null);
    }

    /*
    * Retrieve a student by ID
    * @param id The ID of the student to be retrieved
    * @return Result containing the student details if found, or an error message if not found
    * */
    @Operation(summary = "Get student by ID", description = "Retrieve details of a student using their ID")
    @GetMapping("/{id}")
    public Result<?> getStudentById(@PathVariable("id") Integer id){
        log.info("Received request to get student by ID: {}", id);
        StudentUpdateDTO student = studentService.getStudentById(id);
        log.info("Returning student details: {}", student);
        return Result.success(student);
    }
    /*
    * Update a student by ID
    * @param studentUpdateDTO Data Transfer Object containing updated student details
    * @return Result indicating the success or failure of the update operations
    * */
    @Operation(summary = "Update student details", description = "Modify the details of an existing student")
    @PutMapping
    @OperationLog
    public Result<?> updateStudentById(@RequestBody StudentUpdateDTO studentUpdateDTO){
        log.info("Received request to update student: {}", studentUpdateDTO);
        studentService.updateStudentById(studentUpdateDTO);
        log.info("Student updated successfully: {}", studentUpdateDTO);
        return Result.success(null);
    }

    /*
    * Update violation count and score for a student by id
    * @param id The ID of the student whose violation score are to be updated
    * @return Result indicating the success or failure of the update operation
    * */
    @Operation(summary = "Update student violation count and score", description = "Modify the violation count and score of a student using their ID")
    @PutMapping("/violation/{id}/{score}")
    @OperationLog
    public Result<?> updateViolationScoreById(@PathVariable("id") Integer id, @PathVariable("score") Integer score){
        log.info("Received request to update violation score for student ID: {}, score: {}", id, score);
        studentService.updateViolationScoreById(id, score);
        log.info("Violation score updated successfully for student ID: {}", id);
        return Result.success(null);
    }
}
