package com.kezhang.tliasbackend.service;


import com.kezhang.tliasbackend.dto.EmployeeLoginInfoDTO;
import com.kezhang.tliasbackend.dto.EmployeeLoginResponseDTO;

public interface LoginService {
    /*
    * 处理登陆的验证
    * */
    EmployeeLoginResponseDTO login(EmployeeLoginInfoDTO employeeLoginInfoDTO);
}
