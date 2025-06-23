package com.kezhang.tliasbackend.controller;

import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.dto.ClazzCallbackUpdateDTO;
import com.kezhang.tliasbackend.dto.ClazzInsertDTO;
import com.kezhang.tliasbackend.dto.ClazzQueryParam;
import com.kezhang.tliasbackend.dto.ClazzResponseDTO;
import com.kezhang.tliasbackend.service.ClazzService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzes")
@Tag(name = "Clazz management controller", description = "APIs of class management")
public class ClazzController {
    private final ClazzService clazzService;
    @Autowired
    public ClazzController(ClazzService clazzService) {
        this.clazzService = clazzService;
    }

    /*
    * Select all classes by condition, used for pagination display
    * @param clazzQueryParam ClazzQueryParam object containing query parameters
    * @return Result containing PageResult of ClazzResponseDTO
    * */
    @Operation(summary = "Select all classes by condition", description = "Select all classes by condition, used for pagination display")
    @GetMapping
    public Result<?> getAllClazzesByCondition(ClazzQueryParam clazzQueryParam) {
        log.info("getAllClazzesByCondition: {}", clazzQueryParam);
        PageResult<ClazzResponseDTO> pageResult = clazzService.getAllClazzesByCondition(clazzQueryParam);
        log.info("getAllClazzesByCondition: {}", pageResult);
        return Result.success(pageResult);
    }

    /*
    * Insert a new class information
    * @param clazzInsertDTO ClazzInsertDTO object containing class information
    * */
    @Operation(summary = "Insert a new class information", description = "Insert a new class information")
    @PostMapping
    public Result<?> insertClazzInfo(@RequestBody ClazzInsertDTO clazzInsertDTO) {
        log.info("insertClazzInfo: {}", clazzInsertDTO);
        clazzService.insertClazzInfo(clazzInsertDTO);
        log.info("insertClazzInfo completed successfully.");
        return Result.success(null);
    }

    /*
    * Delete class information by ID
    * @param ids List of class IDs to be deleted
    * */
    @Operation(summary = "Delete class information by ID", description = "Delete class information by ID")
    @DeleteMapping
    public Result<?> deleteClazzById(@RequestParam("ids") List<Integer> ids){
        log.info("deleteClazzById: {}", ids);
        clazzService.deleteClazzById(ids);
        log.info("deleteClazzById completed successfully.");
        return Result.success(null);
    }

    /*
    * Retrieve class information by ID
    * */
    @Operation(summary = "Retrieve class information by ID", description = "Retrieve class information by ID")
    @GetMapping("/{id}")
    public Result<?> getClazzInfoById(@PathVariable("id") Integer id){
        log.info("getClazzInfoById: {}", id);
        ClazzCallbackUpdateDTO clazzCallbackUpdateDTO = clazzService.getClazzInfoById(id);
        log.info("getClazzInfoById completed successfully: {}", clazzCallbackUpdateDTO);
        return Result.success(clazzCallbackUpdateDTO);
    }
    /*
    * Update class information by condition
    * */
    @Operation(summary = "Update class information by condition", description = "Update class information by condition")
    @PutMapping
    public Result<?> updateClazzByCondition(@RequestBody ClazzCallbackUpdateDTO clazzCallbackUpdateDTO) {
        log.info("updateClazzByCondition: {}", clazzCallbackUpdateDTO);
        clazzService.updateClazzByCondition(clazzCallbackUpdateDTO);
        log.info("updateClazzByCondition completed successfully.");
        return Result.success(null);
    }
}
