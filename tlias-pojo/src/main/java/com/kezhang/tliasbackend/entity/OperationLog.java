package com.kezhang.tliasbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationLog {
    private Integer id;
    private Integer operateEmpId;
    private String operateEmpName;
    private LocalDateTime operateTime;
    private String javaClassName;
    private String javaMethodName;
    private String javaMethodParams;
    private String javaMethodReturn;
    private Long costTime;
    private Integer isSuccess; // 0:失败，1:成功
}
