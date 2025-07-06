package com.kezhang.tliasbackend.mapper;

import com.kezhang.tliasbackend.dto.OperationLogQueryParam;
import com.kezhang.tliasbackend.entity.OperationLog;

import java.util.List;

public interface OperationLogMapper {
    /*
    * 插入操作日志
    * */
    void insertLog(OperationLog operationLog);

    /*
    * 条件分页查询操作日志
    * */
    List<OperationLog> getAllOperationLogsByCondition(OperationLogQueryParam operationLogQueryParam);
}
