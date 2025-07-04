package com.kezhang.tliasbackend.mapper;

import com.kezhang.tliasbackend.entity.OperationLog;

public interface OperationLogMapper {
    /*
    * 插入操作日志
    * */
    void insertLog(OperationLog operationLog);
}
