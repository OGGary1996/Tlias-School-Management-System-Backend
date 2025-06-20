package com.kezhang.tliasbackend.service.impl;

import com.kezhang.tliasbackend.constant.ErrorCodeEnum;
import com.kezhang.tliasbackend.dto.PositionInsertDTO;
import com.kezhang.tliasbackend.dto.PositionResponseDTO;
import com.kezhang.tliasbackend.dto.PositionUpdateDTO;
import com.kezhang.tliasbackend.entity.Position;
import com.kezhang.tliasbackend.exception.PositionNotFoundException;
import com.kezhang.tliasbackend.mapper.PositionMapper;
import com.kezhang.tliasbackend.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class PositionServiceImpl implements PositionService {
    private final PositionMapper positionMapper;
    @Autowired
    public PositionServiceImpl(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }

    /*
    * 流程：
    *  1. 从数据库中查询所有岗位信息
    *  2. 将查询结果转换为DTO对象
    *  3. 返回DTO对象列表
    * */
    @Override
    public List<PositionResponseDTO> getAllPositions() {
        log.info("Fetching all positions from the database started.");
        List<PositionResponseDTO> list = positionMapper.selectAllPositions().stream()
                .map(position -> {
                    PositionResponseDTO positionResponseDTO = new PositionResponseDTO();
                    BeanUtils.copyProperties(position, positionResponseDTO);
                    return positionResponseDTO;
                }).toList();
        log.info("Fetching all positions from the database completed. Total positions size: {}", list.size());
        return list;
    }

    /*
    * 流程：
    *  1. 从数据库中删除指定ID的岗位
    *  2. 返回受影响的行数
    * */
    @Override
    public void deletePositionById(Integer id) {
        log.info("Deleting position with ID: {}", id);
        Integer i = positionMapper.deletePositionById(id);
        if (i == 0){
            throw new PositionNotFoundException(
                    ErrorCodeEnum.POSITION_NOT_FOUND.getCode(),
                    ErrorCodeEnum.POSITION_NOT_FOUND.getMessage());
        }

        log.info("Deletion of position with ID: {} completed. Rows affected: {}", id, i);
    }


    /*
    * 流程：
    * 1. 将传入的DTO对象转换为实体对象
    * 2. 调用Mapper层的插入方法，将实体对象插入到数据库中
    * 3. 返回受影响的行数
    * */
    @Override
    public void insertPosition(PositionInsertDTO positionInsertDTO) {
        log.debug("Starting convert PositionInsertDTO to Position entity.");
        Position position = new Position();
        BeanUtils.copyProperties(positionInsertDTO, position);
        log.debug("Conversion completed. Inserting position into the database.");
        log.info("Inserting position with name: {}", position.getName());
        Integer i = positionMapper.insertPosition(position);
        log.info("Insertion completed. Rows affected: {}", i);
    }

    /*
    * 流程：
    * 1. 从数据库中根据ID查询岗位信息
    * 2. 将查询结果转换为DTO对象
    * 3. 返回DTO对象
    * */
    @Override
    public PositionResponseDTO getPositionById(Integer id) {
        log.info("Retrieved position with ID: {}", id);
        Position position  = positionMapper.selectPositionById(id);
        if (position == null) {
            throw new PositionNotFoundException(
                    ErrorCodeEnum.POSITION_NOT_FOUND.getCode(),
                    ErrorCodeEnum.POSITION_NOT_FOUND.getMessage());
        }
        log.info("getPositionById completed. Position details: {}", position);

        log.debug("Starting convert Position entity to PositionResponseDTO.");
        PositionResponseDTO positionResponseDTO = new PositionResponseDTO();
        BeanUtils.copyProperties(position, positionResponseDTO);
        log.debug("Conversion completed. Returning PositionResponseDTO.");
        return positionResponseDTO;
    }

    /*
    * 流程
    * 1. 将传入的DTO对象转换为实体对象
    * 2. 调用Mapper层的更新方法，将实体对象更新到数据库中
    * 3. 返回受影响的行数
    * */
    @Override
    public void updatePositionById(PositionUpdateDTO positionUpdateDTO) {
        log.debug("Starting convert PositionUpdateDTO to Position entity.");
        Position position = new Position();
        BeanUtils.copyProperties(positionUpdateDTO, position);
        log.debug("Conversion completed. Updating position in the database.");

        log.info("Updating position with ID: {}", position.getId());
        Integer i = positionMapper.updatePositionById(position);
        if (i == 0) {
            throw new PositionNotFoundException(
                    ErrorCodeEnum.POSITION_NOT_FOUND.getCode(),
                    ErrorCodeEnum.POSITION_NOT_FOUND.getMessage());
        }
        log.info("Update completed. Rows affected: {}", i);
    }
} 