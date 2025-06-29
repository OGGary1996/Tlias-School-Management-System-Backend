package com.kezhang.tliasbackend.mapper;


import com.kezhang.tliasbackend.dto.StudentUpdateDTO;
import com.kezhang.tliasbackend.dto.StudentResponseDTO;
import com.kezhang.tliasbackend.entity.Student;

import java.util.List;

public interface StudentMapper {
    /*
    * 获取学生信息，分页条件查询
    * */
    List<StudentResponseDTO> getStudentByCondition(Student student);

    /*
    * 插入学生信息
    * */
    void insertStudent(Student student);

    /*
    * 删除学生信息
    * */
    Integer deleteStudentById(List<Integer> ids);

    /*
    * 回显单个学生信息
    * */
    Student getStudentById(Integer id);
    /*
    * 修改单个学生信息
    * */
    Integer updateStudentById(Student student);

    /*
    * 更新违纪分数
    * */
    Integer updateViolationScoreById(Integer id, Integer score);
}
