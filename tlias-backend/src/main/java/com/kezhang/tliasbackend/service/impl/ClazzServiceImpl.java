package com.kezhang.tliasbackend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.constant.ErrorCodeEnum;
import com.kezhang.tliasbackend.dto.ClazzInsertDTO;
import com.kezhang.tliasbackend.dto.ClazzQueryParam;
import com.kezhang.tliasbackend.dto.ClazzResponseDTO;
import com.kezhang.tliasbackend.entity.Clazz;
import com.kezhang.tliasbackend.exception.ClazzNotAllowToDeleteException;
import com.kezhang.tliasbackend.exception.EmployeeNotFoundException;
import com.kezhang.tliasbackend.exception.SubjectNotFoundException;
import com.kezhang.tliasbackend.mapper.ClazzMapper;
import com.kezhang.tliasbackend.mapper.EmployeeMapper;
import com.kezhang.tliasbackend.mapper.SubjectMapper;
import com.kezhang.tliasbackend.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ClazzServiceImpl implements ClazzService {
    private final ClazzMapper clazzMapper; // Assuming ClazzMapper is injected here
    private final SubjectMapper subjectMapper; // Assuming SubjectMapper is injected here
    private final EmployeeMapper employeeMapper;
    @Autowired
    public ClazzServiceImpl(ClazzMapper clazzMapper, SubjectMapper subjectMapper, EmployeeMapper employeeMapper) {
        this.clazzMapper = clazzMapper;
        this.subjectMapper = subjectMapper;
        this.employeeMapper = employeeMapper;
    }


    /*
    * 流程：
    *  1. 根据传递的param类获取到查询条件中的分页参数page和pageSize
    *  2. 使用PageHelper中的startPage方法处理分页参数
    *  3. 调用Mapper中的selectAllClazzesByCondition方法查询所有的班级信息
    *  4. 将查询结果封装为PageInfo对象,获取到总记录数和当前页的班级信息
    *  5. 将总记录数和当前页的班级信息封装为PageResult对象
    * */
    @Override
    public PageResult<ClazzResponseDTO> getAllClazzesByCondition(ClazzQueryParam clazzQueryParam) {
        log.info("getAllClazzesByCondition: {}", clazzQueryParam);

        // 使用startPage方法处理分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        // 调用Mapper中的selectAllClazzesByCondition方法查询所有的班级信息
        List<ClazzResponseDTO> clazzResponseDTOS = clazzMapper.selectAllClazzesByCondition(clazzQueryParam);
        log.info("Found {} classes for the given query parameters.", clazzResponseDTOS.size());

        // 封装查询结果为PageInfo对象
        PageInfo<ClazzResponseDTO> pageInfo = new PageInfo<>(clazzResponseDTOS);
        return PageResult.<ClazzResponseDTO>builder()
                .total(pageInfo.getTotal())
                .records(pageInfo.getList()) // 获取当前页的班级信息
                .build();
    }

    /*
    * 流程：
    *  1. 首先接受DTO中的masterName和subjectName,需要进行判断
    *  2. 通过EmployeeMapper, SubjectMapper等获取到对应的masterId和subjectId对象
    *  3. 将所有的信息封装到Clazz Entity中
    *  4. 调用Mapper中的insertClazz方法插入班级信息
    * */
    @Override
    public void insertClazzInfo(ClazzInsertDTO clazzInsertDTO) {
        log.info("insertClazzInfo: {}", clazzInsertDTO);

        // 获取masterId和subjectId
        Integer masterId = employeeMapper.selectEmployeeIdByName(clazzInsertDTO.getMasterName());
        if (masterId == null){
            log.error("Master not fdoun for name: {}", clazzInsertDTO.getMasterName());
            throw new EmployeeNotFoundException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND.getCode(), ErrorCodeEnum.EMPLOYEE_NOT_FOUND.getMessage());
        }
        Integer subjectId = subjectMapper.selectSubjectIdByName(clazzInsertDTO.getSubjectName());
        if (subjectId == null){
            log.error("Subject not found for name: {}", clazzInsertDTO.getSubjectName());
            throw new SubjectNotFoundException(ErrorCodeEnum.SUBJECT_NOT_FOUND.getCode(), ErrorCodeEnum.SUBJECT_NOT_FOUND.getMessage());
        }
        log.info("Master ID: {}, Subject ID: {}", masterId, subjectId);

        // 封装Clazz Entity
        Clazz clazz = new Clazz();
        BeanUtils.copyProperties(clazzInsertDTO, clazz);
        clazz.setMasterId(masterId);
        clazz.setSubjectId(subjectId);
        log.info("Clazz Entity: {}", clazz);

        // 调用Mapper中的insertClazz方法插入班级信息
        clazzMapper.insertClazz(clazz);
        log.info("Inserted class information successfully: {}", clazzInsertDTO.getName());
    }

    /*
    * 流程：
    *  1.调用Mapper中的getClazzStatusById方法获取班级的status状态，查看是否可以删除
    *  2.如果不能删除，则抛出异常ClazzNotAllowToDeleteException
    *  3.如果可以删除，则调用Mapper中的deleteClazzById方法删除班级信息
    * */
    @Override
    public void deleteClazzById(List<Integer> ids){
        log.info("deleteClazzById: {}", ids);

        // 获取班级的status状态
        List<String> statusList = clazzMapper.getClazzStatusById(ids);
        log.info("Status list for classes with IDs {}: {}", ids, statusList);
        // 检查是否有班级状态不允许删除
        statusList.forEach(status -> {
            if (status.equals("Ongoing")){
                log.error("Cannot delete class with ongoing status.");
                throw new ClazzNotAllowToDeleteException(ErrorCodeEnum.CLAZZ_NOT_ALLOWED_TO_DELETE.getCode(), ErrorCodeEnum.CLAZZ_NOT_ALLOWED_TO_DELETE.getMessage());
            }
        });
        // 如果所有班级状态都允许删除，则调用Mapper中的deleteClazzById方法删除班级信息
        clazzMapper.deleteClazzById(ids);
        log.info("Deleted classes with IDs: {}", ids);
    }
}
