package com.kezhang.tliasbackend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.dto.StudentInsertDTO;
import com.kezhang.tliasbackend.dto.StudentQueryParam;
import com.kezhang.tliasbackend.dto.StudentResponseDTO;
import com.kezhang.tliasbackend.dto.StudentUpdateDTO;
import com.kezhang.tliasbackend.entity.Student;
import com.kezhang.tliasbackend.mapper.StudentMapper;
import com.kezhang.tliasbackend.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    /*
    * 流程：
    *  1. 接收查询参数中的page，pageSize等分页信息
    *  2. 使用PageHelper进行分页处理
    *  3. 将剩下的参数封装到Student Entity中
    *  4. 调用Mapper层的getStudentByCondition方法进行查询
    * */
    @Override
    public PageResult<StudentResponseDTO> getStudentByCondition(StudentQueryParam studentQueryParam) {
        log.info("Received student query parameters: {}", studentQueryParam);

        // 获取分页信息
        Integer page = studentQueryParam.getPage();
        Integer pageSize = studentQueryParam.getPageSize();
        PageHelper.startPage(page, pageSize);
        log.info("Starting pagination with page: {}, pageSize: {}", page, pageSize);

        // 将查询参数转换为Student对象
        log.info("Converting StudentQueryParam to Student entity");
        Student student = new Student();
        BeanUtils.copyProperties(studentQueryParam, student);
        log.info("Converted Student entity: {}", student);

        // 调用Mapper层查询学生信息
        List<StudentResponseDTO> studentList = studentMapper.getStudentByCondition(student);
        log.info("Retrieved student list: {}", studentList);

        // 返回分页结果
        PageInfo<StudentResponseDTO> pageInfo = new PageInfo<>(studentList);
        log.info("PageInfo created with total: {}, pages: {}", pageInfo.getTotal(), pageInfo.getPages());
        return PageResult.<StudentResponseDTO>builder()
                .total(pageInfo.getTotal())
                .records(pageInfo.getList())
                .build();
    }

    /*
    * 流程:
    *  1. 接收StudentInsertDTO对象
    *  2. 将DTO对象转换为Student实体
    *  3. 调用Mapper层的insert方法进行插入
    * */
    @Override
    public void insertStudent(StudentInsertDTO studentInsertDTO) {
        log.info("Received student insert request: {}", studentInsertDTO);
        // 将DTO转换为实体
        Student student = new Student();
        BeanUtils.copyProperties(studentInsertDTO, student);
        log.info("Converted Student entity for insertion: {}", student);
        // 调用Mapper层进行插入
        studentMapper.insertStudent(student);
        log.info("Student inserted successfully: {}", student.getId());
    }

    /*
    * 流程：
    *  1. 接收学生ID列表
    *  2. 调用Mapper层的delete方法进行删除
    * */
    @Override
    public void deleteStudentById(List<Integer> ids) {
        log.info("Received request to delete students with IDs: {}", ids);
        studentMapper.deleteStudentById(ids);
        log.info("Students with IDs {} deleted successfully", ids);
    }

    /*
    * 流程：
    *  1. 接收学生ID
    *  2. 调用Mapper层的getStudentById方法获取学生信息
    *  3. 转换并返回StudentUpdateDTO对象
    * */
    @Override
    public StudentUpdateDTO getStudentById(Integer id) {
        log.info("Received request to get student by ID: {}", id);
        Student student = studentMapper.getStudentById(id);
        log.info("Retrieved student entity: {}", student);
        StudentUpdateDTO studentUpdateDTO = new StudentUpdateDTO();
        BeanUtils.copyProperties(student, studentUpdateDTO);
        log.info("Converted StudentUpdateDTO: {}", studentUpdateDTO);

        return studentUpdateDTO;
    }
    /*
    * 流程：
    *  1. 接收StudentUpdateDTO对象
    *  2. 将DTO对象转换为Student实体
    *  3. 调用Mapper层的update方法进行更新
    * */
    @Override
    public void updateStudentById(StudentUpdateDTO studentUpdateDTO) {
        log.info("Received request to update student: {}", studentUpdateDTO);
        Student student = new Student();
        BeanUtils.copyProperties(studentUpdateDTO, student);
        log.info("Converted Student entity for update: {}", student);
        studentMapper.updateStudentById(student);
        log.info("Student with ID {} updated successfully", student.getId());
    }

    /*
    * 流程：
    *  1. 接收学生ID和新的违纪分数
    *  2. 调用Mapper层的updateViolationScoreById方法进行更新
    *  3. 自动累加违纪分数，违纪次数每次操作+1
    * */
    @Override
    public void updateViolationScoreById(Integer id, Integer score) {
        log.info("Received request to update violation score for student ID: {}, score: {}", id, score);
        studentMapper.updateViolationScoreById(id, score);
        log.info("Violation score for student ID {} updated successfully with score: {}", id, score);
    }
}
