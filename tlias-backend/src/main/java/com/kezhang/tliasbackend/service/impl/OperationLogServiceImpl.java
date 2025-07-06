package com.kezhang.tliasbackend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.dto.OperationLogQueryParam;
import com.kezhang.tliasbackend.entity.OperationLog;
import com.kezhang.tliasbackend.mapper.OperationLogMapper;
import com.kezhang.tliasbackend.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OperationLogServiceImpl implements OperationLogService {
    private final OperationLogMapper operationLogMapper;
    @Autowired
    public OperationLogServiceImpl(OperationLogMapper operationLogMapper) {
        this.operationLogMapper = operationLogMapper;
    }

    /*
    * 流程：
    *  1. 接收前端传入的查询参数
    *  2. 交给PageHelper处理分页信息
    *  3. 调用Mapper查询数据
    *  4. 将查询结果封装到PageInfo中
    *  5. 构建PageResult对象作为结果返回
    * */
    @Override
    public PageResult<OperationLog> getAllOperationLogsByCondition(OperationLogQueryParam operationLogQueryParam) {
        log.info("Received operation log query: {}", operationLogQueryParam);

        PageHelper.startPage(operationLogQueryParam.getPage(), operationLogQueryParam.getPageSize());
        List<OperationLog> operationLogs = operationLogMapper.getAllOperationLogsByCondition(operationLogQueryParam);
        log.info("Fetched {} operation logs", operationLogs.size());

        PageInfo<OperationLog> pageInfo = new PageInfo<>(operationLogs);

        return PageResult.<OperationLog>builder()
                .total(pageInfo.getTotal())
                .records(pageInfo.getList())
                .build();
    }


}
