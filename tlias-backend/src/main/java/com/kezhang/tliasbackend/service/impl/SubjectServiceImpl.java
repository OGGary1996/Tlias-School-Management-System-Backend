package com.kezhang.tliasbackend.service.impl;

import com.kezhang.tliasbackend.constant.ErrorCodeEnum;
import com.kezhang.tliasbackend.dto.SubjectInsertDTO;
import com.kezhang.tliasbackend.dto.SubjectResponseDTO;
import com.kezhang.tliasbackend.dto.SubjectUpdateDTO;
import com.kezhang.tliasbackend.entity.Subject;
import com.kezhang.tliasbackend.exception.SubjectNotFoundException;
import com.kezhang.tliasbackend.mapper.SubjectMapper;
import com.kezhang.tliasbackend.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectMapper subjectMapper;
    @Autowired
    public SubjectServiceImpl(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    /*
    * 流程：
    *  1. 从数据库中查询所有课程信息
    *  2. 将查询结果转换为DTO对象
    *  3. 返回DTO对象列表
    * */
    @Override
    public List<SubjectResponseDTO> getAllSubjects() {
        log.info("Fetching all subjects from the database started.");
        List<SubjectResponseDTO> list = subjectMapper.selectAllSubjects().stream()
                .map(subject -> {
                    SubjectResponseDTO subjectResponseDTO = new SubjectResponseDTO();
                    BeanUtils.copyProperties(subject, subjectResponseDTO);
                    return subjectResponseDTO;
                }).toList();
        log.info("Fetching all subjects from the database completed. Total subjects size: {}", list.size());
        return list;
    }

    /*
    * 流程：
    *  1. 从数据库中删除指定ID的课程
    *  2. 返回受影响的行数
    * */
    @Override
    public void deleteSubjectById(Integer id) {
        log.info("Deleting subject with ID: {}", id);
        Integer i = subjectMapper.deleteSubjectById(id);
        if (i == 0){
            throw new SubjectNotFoundException(
                    ErrorCodeEnum.SUBJECT_NOT_FOUND.getCode(),
                    ErrorCodeEnum.SUBJECT_NOT_FOUND.getMessage());
        }

        log.info("Deletion of subject with ID: {} completed. Rows affected: {}", id, i);
    }


    /*
    * 流程：
    * 1. 将传入的DTO对象转换为实体对象
    * 2. 调用Mapper层的插入方法，将实体对象插入到数据库中
    * 3. 返回受影响的行数
    * */
    @Override
    public void insertSubject(SubjectInsertDTO subjectInsertDTO) {
        log.debug("Starting convert SubjectInsertDTO to Subject entity.");
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectInsertDTO, subject);
        log.debug("Conversion completed. Inserting subject into the database.");
        log.info("Inserting subject with name: {}", subject.getName());
        Integer i = subjectMapper.insertSubject(subject);
        log.info("Insertion completed. Rows affected: {}", i);
    }

    /*
    * 流程：
    * 1. 从数据库中根据ID查询课程信息
    * 2. 将查询结果转换为DTO对象
    * 3. 返回DTO对象
    * */
    @Override
    public SubjectResponseDTO getSubjectById(Integer id) {
        log.info("Retrieved subject with ID: {}", id);
        Subject subject  = subjectMapper.selectSubjectById(id);
        if (subject == null) {
            throw new SubjectNotFoundException(
                    ErrorCodeEnum.SUBJECT_NOT_FOUND.getCode(),
                    ErrorCodeEnum.SUBJECT_NOT_FOUND.getMessage());
        }
        log.info("getSubjectById completed. Subject details: {}", subject);

        log.debug("Starting convert Subject entity to SubjectResponseDTO.");
        SubjectResponseDTO subjectResponseDTO = new SubjectResponseDTO();
        BeanUtils.copyProperties(subject, subjectResponseDTO);
        log.debug("Conversion completed. Returning SubjectResponseDTO.");
        return subjectResponseDTO;
    }

    /*
    * 流程
    * 1. 将传入的DTO对象转换为实体对象
    * 2. 调用Mapper层的更新方法，将实体对象更新到数据库中
    * 3. 返回受影响的行数
    * */
    @Override
    public void updateSubjectById(SubjectUpdateDTO subjectUpdateDTO) {
        log.debug("Starting convert SubjectUpdateDTO to Subject entity.");
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectUpdateDTO, subject);
        log.debug("Conversion completed. Updating subject in the database.");

        log.info("Updating subject with ID: {}", subject.getId());
        Integer i = subjectMapper.updateSubjectById(subject);
        if (i == 0) {
            throw new SubjectNotFoundException(
                    ErrorCodeEnum.SUBJECT_NOT_FOUND.getCode(),
                    ErrorCodeEnum.SUBJECT_NOT_FOUND.getMessage());
        }
        log.info("Update completed. Rows affected: {}", i);
    }
} 