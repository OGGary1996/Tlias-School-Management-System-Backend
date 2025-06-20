package com.kezhang.tliasbackend.controller;

import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.dto.DepartmentEmployeeCountResponseDTO;
import com.kezhang.tliasbackend.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/reports")
@Tag(name = "ReportController", description = "Controller for managing reports")
public class ReportController {
    private final ReportService reportService;
    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /*
     * 用于处理前端的部门员工报表请求
     * @return Result<?> 返回报表数据
     */
    @GetMapping("/department-employee-count")
    @Operation(summary = "Get data for employee per department report ", description = "Retrieve data for employee report")
    public Result<?> departmentEmployeeReport(){
        log.info("Received request for department employee report.");
        DepartmentEmployeeCountResponseDTO departmentEmployeeCountResponseDTO = reportService.getDepartmentEmployeeCount();
        log.info("Returning department employee report data: {}", departmentEmployeeCountResponseDTO);
        return Result.success(departmentEmployeeCountResponseDTO);
    }
}
