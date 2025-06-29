package com.kezhang.tliasbackend.service.impl;

import com.kezhang.tliasbackend.constant.ErrorCodeEnum;
import com.kezhang.tliasbackend.dto.EmployeeLoginInfoDTO;
import com.kezhang.tliasbackend.dto.EmployeeLoginResponseDTO;
import com.kezhang.tliasbackend.entity.Employee;
import com.kezhang.tliasbackend.exception.UsernameOrPasswordErrorException;
import com.kezhang.tliasbackend.mapper.EmployeeMapper;
import com.kezhang.tliasbackend.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    private final EmployeeMapper employeeMapper;
    @Autowired
    public LoginServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    /*
    * 流程：
    *  1. 接收前端传来的登录信息,转换为Employee Entity
    *  2. 调用EmployeeMapper的查询方法，查询是否存在该员工
    *  3. 如果返回的结果为null,手动抛出用户名不存在或者密码错误的异常
    *  4. 如果查询结果不为null，则转换为EmployeeLoginResponseDTO
    * */
    @Override
    public EmployeeLoginResponseDTO login(EmployeeLoginInfoDTO employeeLoginInfoDTO) {
        log.info("Login request received, processing login for employee: {}", employeeLoginInfoDTO.getUsername());
        // 转换为 Employee Entity
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeLoginInfoDTO, employee);
        Employee employeeMatched = employeeMapper.selectEmployeeByUsernameAndPassword(employee);
        if (employeeMatched == null){
            throw new UsernameOrPasswordErrorException(
                    ErrorCodeEnum.USERNAME_OR_PASSWORD_ERROR.getCode(),
                    ErrorCodeEnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
        }
        log.info("Login successful for employee: {}", employeeMatched.getUsername());

        // 转换为 EmployeeLoginResponseDTO
        EmployeeLoginResponseDTO employeeLoginResponseDTO = new EmployeeLoginResponseDTO();
        BeanUtils.copyProperties(employeeMatched, employeeLoginResponseDTO);
        // 设置 token，暂时设置为null，交给Controller层处理HTTP相关的请求和响应
        employeeLoginResponseDTO.setToken(null);

        // 返回登录响应 DTO
        log.info("Returning login response for employee: {}", employeeLoginResponseDTO.getUsername());
        return employeeLoginResponseDTO;
    }
}
