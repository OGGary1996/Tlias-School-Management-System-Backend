package com.kezhang.tliasbackend.service;

import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.dto.OperationLogQueryParam;
import com.kezhang.tliasbackend.entity.OperationLog;


public interface OperationLogService {
    /*
    * 查询所有的操作日志
    * */
    PageResult<OperationLog> getAllOperationLogsByCondition(OperationLogQueryParam operationLogQueryParam);
}
