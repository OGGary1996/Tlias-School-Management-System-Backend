package com.kezhang.tliasbackend.service;

import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.dto.StudentInsertDTO;
import com.kezhang.tliasbackend.dto.StudentQueryParam;
import com.kezhang.tliasbackend.dto.StudentResponseDTO;
import com.kezhang.tliasbackend.dto.StudentUpdateDTO;

import java.util.List;

public interface StudentService {
    /*
    * 获取学生信息，分页条件查询
    * */
    PageResult<StudentResponseDTO> getStudentByCondition(StudentQueryParam studentQueryParam);

    /*
    * 插入学生信息
    * */
    void insertStudent(StudentInsertDTO studentInsertDTO);

    /*
    * 删除学生信息
    * */
    void deleteStudentById(List<Integer> ids);

    /*
    * 回显单个学生信息
    * */
    StudentUpdateDTO getStudentById(Integer id);
    /*
    * 修改单个学生信息
    * */
    void updateStudentById(StudentUpdateDTO studentUpdateDTO);

    /*
    * 更新违纪分数
    * */
    void updateViolationScoreById(Integer id, Integer score);
}
