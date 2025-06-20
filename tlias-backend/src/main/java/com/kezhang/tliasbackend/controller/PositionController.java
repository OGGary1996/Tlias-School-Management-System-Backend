package com.kezhang.tliasbackend.controller;

import com.kezhang.tliasbackend.dto.PositionInsertDTO;
import com.kezhang.tliasbackend.dto.PositionResponseDTO;
import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.dto.PositionUpdateDTO;
import com.kezhang.tliasbackend.service.PositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/positions")
@Tag(name = "PositionController", description = "Controller for managing positions")
public class PositionController {
    private final PositionService positionService;
    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    @Operation(summary = "List all positions", description = "Retrieve a list of all positions in the system")
    public Result<?> listAllPositions(){
        log.info("Fetching all positions from the service started.");
        List<PositionResponseDTO> list = positionService.getAllPositions();
        log.info("Fetching all positions from the service completed. Total positions size: {}", list.size());
        return Result.success(list);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a position", description = "Delete a position by its ID")
    public Result<?> deletePosition(@PathVariable Integer id){
        log.info("Deleting position with ID: {}", id);
        positionService.deletePositionById(id);
        log.info("Deletion of position with ID: {} completed.", id);
        return Result.success(null);
    }

    @PostMapping
    @Operation(summary = "Create a new position", description = "Insert a new position into the system")
    public Result<?> createPosition(@RequestBody @Valid PositionInsertDTO positionInsertDTO){
        log.info("Creating a new position with details: {}", positionInsertDTO);
        positionService.insertPosition(positionInsertDTO);
        log.info("Creation of new position completed." );
        return Result.success(null);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get position by ID", description = "Retrieve a position by its ID")
    public Result<?> getPosition(@PathVariable Integer id){
        log.info("Fetching position with ID: {}", id);
        PositionResponseDTO positionResponseDTO = positionService.getPositionById(id);
        log.info("Fetching position with ID: {} completed. Position details: {}", id, positionResponseDTO);
        return Result.success(positionResponseDTO);
    }

    @PutMapping
    @Operation(summary = "Update a position", description = "Update an existing position's details")
    public Result<?> updatePosition(@RequestBody @Valid PositionUpdateDTO positionUpdateDTO){
        log.info("Updating position with details: {}", positionUpdateDTO);
        positionService.updatePositionById(positionUpdateDTO);
        log.info("Update of position completed.");
        return Result.success(null);
    }
} 