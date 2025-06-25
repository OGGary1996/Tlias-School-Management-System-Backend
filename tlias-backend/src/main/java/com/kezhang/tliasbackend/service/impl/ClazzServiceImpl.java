package com.kezhang.tliasbackend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.constant.ErrorCodeEnum;
import com.kezhang.tliasbackend.dto.ClazzInsertDTO;
import com.kezhang.tliasbackend.dto.ClazzQueryParam;
import com.kezhang.tliasbackend.dto.ClazzResponseDTO;
import com.kezhang.tliasbackend.dto.ClazzUpdateDTO;
import com.kezhang.tliasbackend.entity.Clazz;
import com.kezhang.tliasbackend.exception.ClazzNotAllowToDeleteException;
import com.kezhang.tliasbackend.mapper.ClazzMapper;
import com.kezhang.tliasbackend.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClazzServiceImpl implements ClazzService {
    private final ClazzMapper clazzMapper; // Assuming ClazzMapper is injected here
    @Autowired
    public ClazzServiceImpl(ClazzMapper clazzMapper) {
        this.clazzMapper = clazzMapper;
    }


    /*
    * 流程：
    *  1. 调用Mapper中的selectAllOngoingAndUpcomingClazzes方法查询所有Ongoing & Upcoming状态的班级信息
    * */
    @Override
    public List<ClazzResponseDTO> getAllOngoingAndUpcomingClazzes() {
        log.info("getAllOngoingAndUpcomingClazzes called.");
        List<ClazzResponseDTO> clazzResponseDTOS = clazzMapper.selectAllOngoingAndUpcomingClazzes();
        if (clazzResponseDTOS.isEmpty()) {
            log.warn("No ongoing or upcoming classes found.");
        } else {
            log.info("Found {} ongoing or upcoming classes.", clazzResponseDTOS.size());
        }
        return clazzResponseDTOS;
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
    *  1. 将ClazzInsertDTO转换为Clazz Entity
    *  2. 调用Mapper中的insertClazz方法插入班级信息
    * */
    @Override
    public void insertClazzInfo(ClazzInsertDTO clazzInsertDTO) {
        log.info("insertClazzInfo: {}", clazzInsertDTO);

        // 封装Clazz Entity
        Clazz clazz = new Clazz();
        BeanUtils.copyProperties(clazzInsertDTO, clazz);
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

    /*
    * 流程：
    *  1. 调用Mapper中的getClazzById方法获取单个班级信息
    *  2. 将获取到的班级信息封装为ClazzUpdateDTO对象
    * */
    @Override
    public ClazzUpdateDTO getClazzInfoById(Integer id) {
        log.info("getClazzInfoById: {}", id);
        Clazz clazz = clazzMapper.getClazzById(id);
        log.info("Retrieved class entity: {}", clazz);
        ClazzUpdateDTO clazzUpdateDTO = new ClazzUpdateDTO();
        BeanUtils.copyProperties(clazz, clazzUpdateDTO);
        log.info("Retrieved class information: {}", clazzUpdateDTO);
        return clazzUpdateDTO;
    }

    /*
    * 流程：
    *  1. 将ClazzUpdateDTO转换为Clazz Entity
    *  2. 调用Mapper中的updateClazzByCondition方法更新班级信息
    * */
    @Override
    public void updateClazzByCondition(ClazzUpdateDTO clazzUpdateDTO) {
        log.info("updateClazzByCondition: {}", clazzUpdateDTO);

        // 封装Clazz Entity
        Clazz clazz = new Clazz();
        BeanUtils.copyProperties(clazzUpdateDTO, clazz);
        log.info("Clazz Entity for update: {}", clazz);

        // 调用Mapper中的updateClazzByCondition方法更新班级信息
        clazzMapper.updateClazzByCondition(clazz);
        log.info("Updated class information successfully: {}", clazzUpdateDTO.getName());
    }
}
