package com.kezhang.tliasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response DTO for class information")
public class ClazzResponseDTO {
    @Schema(description = " Unique identifier for the class")
    private Integer id;
    @Schema(description = "Name of the class")
    private String name;
    @Schema(description = "Room where the class is held")
    private String room;
    @Schema(description = "ID of the master (teacher) for the class")
    private Integer masterId;
    @Schema(description = "Name of the master (teacher) for the class")
    private String masterName; // reference to employee table
    @Schema(description = "Begin date for the class")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 用于解析日期格式
    private LocalDate beginDate;
    @Schema(description = "End date for the class")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 用于解析日期格式
    private LocalDate endDate;
    @Schema(description = "Created time of the class record")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 用于解析日期时间格式
    private LocalDateTime createTime;
    @Schema(description = "Last updated time of the class record")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 用于解析日期时间格式
    private LocalDateTime updateTime;
    @Schema(description = "Status of the class, e.g., Finished, Ongoing, Upcoming")
    private String status; // e.g., "Finished", "Ongoing", "Upcoming"
}
