package com.kezhang.tliasbackend.service;


import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.dto.ClazzCallbackUpdateDTO;
import com.kezhang.tliasbackend.dto.ClazzInsertDTO;
import com.kezhang.tliasbackend.dto.ClazzQueryParam;
import com.kezhang.tliasbackend.dto.ClazzResponseDTO;

import java.util.List;

public interface ClazzService {
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
    * 获取单个班级信息
    * */
    ClazzCallbackUpdateDTO getClazzInfoById(Integer id);
    /*
    * 修改单个班级信息
    * */
    void updateClazzByCondition(ClazzCallbackUpdateDTO clazzCallbackUpdateDTO);
}
