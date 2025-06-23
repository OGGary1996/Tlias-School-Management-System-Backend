package com.kezhang.tliasbackend.mapper;

import com.kezhang.tliasbackend.dto.ClazzQueryParam;
import com.kezhang.tliasbackend.dto.ClazzResponseDTO;
import com.kezhang.tliasbackend.entity.Clazz;

import java.util.List;

public interface ClazzMapper {
    /*
    * 查询所有的班级信息，用于分页查询展示
    * */
    List<ClazzResponseDTO> selectAllClazzesByCondition(ClazzQueryParam clazzQueryParam);

    /*
    * 新增单个班级信息
    * */
    void insertClazz(Clazz clazz);

    /*
    * 获取班级的status状态，用于删除班级之前的检查
    * */
    List<String> getClazzStatusById(List<Integer> ids);
    /*
    * 删除单个班级信息
    * */
    void deleteClazzById(List<Integer> ids);
}
