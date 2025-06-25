package com.kezhang.tliasbackend.service;


import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.dto.ClazzInsertDTO;
import com.kezhang.tliasbackend.dto.ClazzQueryParam;
import com.kezhang.tliasbackend.dto.ClazzResponseDTO;
import com.kezhang.tliasbackend.dto.ClazzUpdateDTO;

import java.util.List;

public interface ClazzService {
    /*
    * 查询所有Ongoing & Upcoming状态的班级信息，用于前端下拉菜单展示
    * */
    List<ClazzResponseDTO> getAllOngoingAndUpcomingClazzes();

    /*
    * 查询所有的班级信息，用于分页查询展示
    * */
    PageResult<ClazzResponseDTO> getAllClazzesByCondition(ClazzQueryParam clazzQueryParam);

    /*
    * 新增单个班级信息
    * */
    void insertClazzInfo(ClazzInsertDTO clazzInsertDTO);

    /*
    * 删除单个班级信息
    * */
    void deleteClazzById(List<Integer> ids);

    /*
    * 获取单个班级信息（用于回显）
    * */
    ClazzUpdateDTO getClazzInfoById(Integer id);
    
    /*
    * 修改单个班级信息
    * */
    void updateClazzByCondition(ClazzUpdateDTO clazzUpdateDTO);
}
