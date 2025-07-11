package com.kezhang.tliasbackend.proxy.invocationHandler;//package com.kezhang.tliasbackend.proxy.invocationHandler;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//
//@Slf4j
//public class TimeInvocationHandler implements InvocationHandler {
//    private final Object target;
//    public TimeInvocationHandler(Object target) {
//        this.target = target;
//    }
//    /*
//    * 流程：
//    *  1. 记录方法开始执行的时间
//    *  2. 执行目标方法
//    *  3. 记录方法执行结束的时间
//    *  4. 计算方法执行的时间差，并记录日志
//    * */
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        Long startTime = System.currentTimeMillis();
//        Object result = method.invoke(target, args);
//        Long endTime = System.currentTimeMillis();
//        log.info("Method {} executed in {} ms", method.getName(), endTime - startTime);
//        return result;
//    }
//}
