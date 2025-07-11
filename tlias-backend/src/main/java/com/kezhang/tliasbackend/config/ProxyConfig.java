//package com.kezhang.tliasbackend.config;
//
//@Component
//public class ProxyConfig {
//    /*
//    * 流程：
//    *  1. 调用工厂类的createTimeProxy方法
//    *  2. 返回代理对象
//    * */
//    @Bean // 与@Component等价，@Bean用于方法上，@Component用于类上；都表示交给Spring容器管理
//    @Primary // 由于方法返回的是EmployeeService的代理对象，所以需要替换掉原本注入的EmployeeServiceImpl对象
//    public EmployeeService createEmployeeServiceTimeProxy(ProxyFactory proxyFactory, EmployeeServiceImpl employeeServiceImpl) {
//        return proxyFactory.createTimeProxy(EmployeeService.class, employeeServiceImpl);
//    }
//}
