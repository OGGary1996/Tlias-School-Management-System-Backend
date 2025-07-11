package com.kezhang.tliasbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private Integer id;
    private String name;
    private String accountNumber;
    private Integer gender;
    private String phone;
    private String idCard;
    private String address;
    private Integer clazzId;
    private Integer isCollege;
    private Integer degree;
    private LocalDate graduationDate;
    private Integer violationCount;
    private Integer violationScore;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
