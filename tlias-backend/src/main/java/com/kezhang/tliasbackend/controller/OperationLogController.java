package com.kezhang.tliasbackend.controller;

import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.dto.OperationLogQueryParam;
import com.kezhang.tliasbackend.entity.OperationLog;
import com.kezhang.tliasbackend.service.OperationLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("operation-log")
@Slf4j
@Tag(name = "Operation Log Management", description = "APIs for managing operation logs")
public class OperationLogController {
    private final OperationLogService operationLogService;
    @Autowired
    public OperationLogController(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @GetMapping
    @Operation(summary = "Get Operation Logs", description = "Retrieve operation logs based on query parameters")
    public Result<?> getAllOperationLogsByCondition(OperationLogQueryParam operationLogQueryParam){
        log.info("Fetching operation logs with query: {}", operationLogQueryParam);

        PageResult<OperationLog> pageResult = operationLogService.getAllOperationLogsByCondition(operationLogQueryParam);
        log.info("Fetched {} operation logs", pageResult.getRecords().size());
        return Result.success(pageResult);
    }
}
