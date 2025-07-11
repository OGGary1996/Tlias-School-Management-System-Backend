package com.kezhang.tliasbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer gender;
    private String phone;
    private Integer jobTitle;
    private Integer departmentId;
    private BigDecimal salary;
    private String image;
    private LocalDate entryDate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
