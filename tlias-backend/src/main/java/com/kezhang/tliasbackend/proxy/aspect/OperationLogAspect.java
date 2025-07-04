package com.kezhang.tliasbackend.proxy.aspect;

import com.kezhang.tliasbackend.common.context.LoginContext;
import com.kezhang.tliasbackend.entity.OperationLog;
import com.kezhang.tliasbackend.mapper.OperationLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {
    /*
    * This aspect is used to log operations performed by the user.
    * It intercepts methods annotated with @OperationLog and logs the operation details.
    * */

    private final OperationLogMapper operationLogMapper;
    @Autowired
    public OperationLogAspect(OperationLogMapper operationLogMapper) {
        this.operationLogMapper = operationLogMapper;
    }

    // pointcut for methods annotated with @OperationLog
    @Pointcut("@annotation(com.kezhang.tliasbackend.annotation.OperationLog)" )
    public void operationLogPointCut(){}; // This method is empty because it is only used to define the pointcut

    // advice for the pointcut can be added here
    /*
    * 核心逻辑：
    *  1. 方法执行前：
    *    1. 设置isSuccess为默认为1
    *    2. 记录获取当前时间
    *    3. 调用工具类中JWT解析的方法，获取当前操作人的ID和姓名
    *  2. 方法如果抛出异常：
    *    1. 将isSuccess设置为0
    *    2. 继续抛出异常，交给全局异常处理器处理
    *  2. finally：
    *    1. 获取方法的类名、方法名、参数、返回值
    *    2. 记录方法执行的耗时
    *    3. 不修改isSuccess的值
    *    4. 封装操作日志信息
    *    5. 执行Mapper操作
    * */
    @Around("operationLogPointCut()")
    public Object logOperation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 设置操作成功标志位，默认为1
        Integer isSuccess = 1; // 1表示成功，0表示失败

        // 获取当前时间
        long startTime = System.currentTimeMillis();
        LocalDateTime startTimeStamp = LocalDateTime.ofInstant(
                java.time.Instant.ofEpochMilli(startTime),
                java.time.ZoneId.systemDefault()
        );
        log.info("Operation started at: {}", startTimeStamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // 获取当前操作人的ID和姓名
        // 在拦截器中已经定义了ThreadLocal来存储用户信息，这里直接从ThreadLocal中获取
        Integer operateEmpId = LoginContext.getId();
        String operateEmpName = LoginContext.getName();
        log.info("Operation performed by user ID: {}, Name: {}", operateEmpId, operateEmpName);

        // 执行目标方法
        Object result = null;
        Exception exception = null;
        try {
            result = proceedingJoinPoint.proceed();
            return result; // 返回目标方法的执行结果
        } catch (Exception e) { // 捕获机场，但是不处理，交给全局异常处理器
            isSuccess = 0; // 设置操作失败标志位
            exception = e;
            // 继续抛出异常,交给全局异常处理器处理,注意，此时必须要再次手动抛出异常，否则异常会被AOP吞掉，不会进入全局异常处理器
            throw exception;
        } finally { // 无论成功或者失败，都执行日志操作
            // 获取方法的类名、方法名、参数、返回值
            String javaClassName = proceedingJoinPoint.getTarget().getClass().getName(); // 获取类名
            String javaMethodName = proceedingJoinPoint.getSignature().getName(); // 获取方法名
            Object[] args = proceedingJoinPoint.getArgs(); // 获取方法参数
            String javaMethodParams = args == null ? "No parameters" : Arrays.toString(args); // 将参数转换为字符串
            String javaMethodReturn = result == null ? "No return value" : result.toString(); // 获取方法返回值
            log.info("Method executed: {}.{} with parameters: {}, return value: {}",
                     javaClassName, javaMethodName, javaMethodParams, javaMethodReturn);

            // 记录方法执行的耗时
            long endTime = System.currentTimeMillis();
            long costTime = endTime - startTime;
            log.info("Operation completed in {} ms", costTime);

            // 封装操作日志信息
            OperationLog op = OperationLog.builder()
                    .operateEmpId(operateEmpId)
                    .operateEmpName(operateEmpName)
                    .operateTime(startTimeStamp)
                    .javaClassName(javaClassName)
                    .javaMethodName(javaMethodName)
                    .javaMethodParams(javaMethodParams)
                    .javaMethodReturn(javaMethodReturn)
                    .costTime(costTime)
                    .isSuccess(isSuccess)
                    .build();

            // 执行Mapper操作
            operationLogMapper.insertLog(op);
        }
    }
}
