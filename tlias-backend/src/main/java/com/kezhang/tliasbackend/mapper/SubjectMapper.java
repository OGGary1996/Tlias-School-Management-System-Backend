package com.kezhang.tliasbackend.mapper;

public interface SubjectMapper {
    /*
    * 根据subject_name查询subject_id
    * */
    Integer selectSubjectIdByName(String subjectName);
}
