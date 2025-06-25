package com.kezhang.tliasbackend.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.constant.ErrorCodeEnum;
import com.kezhang.tliasbackend.dto.*;
import com.kezhang.tliasbackend.entity.Employee;
import com.kezhang.tliasbackend.entity.EmployeeHistory;
import com.kezhang.tliasbackend.exception.EmployeeNotFoundException;
import com.kezhang.tliasbackend.mapper.DepartmentMapper;
import com.kezhang.tliasbackend.mapper.EmployeeHistoryMapper;
import com.kezhang.tliasbackend.mapper.EmployeeMapper;
import com.kezhang.tliasbackend.mapper.PositionMapper;
import com.kezhang.tliasbackend.service.EmployeeService;
import com.kezhang.tliasbackend.utils.AliyunOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final EmployeeHistoryMapper employeeHistoryMapper;
    private final AliyunOssUtil aliyunOssUtil;
    private final DepartmentMapper departmentMapper;
    private final PositionMapper positionMapper;
    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper, EmployeeHistoryMapper employeeHistoryMapper, AliyunOssUtil aliyunOssUtil, DepartmentMapper departmentMapper, PositionMapper positionMapper) {
        this.employeeMapper = employeeMapper;
        this.employeeHistoryMapper = employeeHistoryMapper;
        this.aliyunOssUtil = aliyunOssUtil;
        this.departmentMapper = departmentMapper;
        this.positionMapper = positionMapper;
    }

    /*
    * 流程：
    * 1. 查询所有员工信息，使用多表查询
    * 2. 将查询结果转换为 EmployeeResponseDTO 对象
    * 3. 返回 EmployeeResponseDTO 对象的列表
    * */
    @Override
    public List<EmployeeResponseDTO> selectAllEmployees() {
        log.info("Fetching all employees with their department names and job titles started.");
        List<EmployeeResponseDTO> employeeList = employeeMapper.selectAllEmployees();
        log.info("Fetched {} employees from the database.", employeeList.size());
        return employeeList;
    }


//    /*
//    * 流程：
//    * 1. 查询所有员工信息，使用多表查询得到 EmployeeResponseDTO 对象
//    * 2. 将 EmployeeResponseDTO 对象列表转换为 PageResult 对象
//    * 3. 返回 PageResult 对象
//    * */
//    @Override
//    public PageResult<EmployeeResponseDTO> selectAllEmployees(Integer page, Integer limit) {
//        log.info("Fetching all employees with their department names and job titles started.");
//        int offset = (page - 1) * limit;
//        List<EmployeeResponseDTO> employeeResponseDTOs = employeeMapper.selectAllEmployees(offset, limit);
//
//        long total = employeeMapper.countAllEmployees();
//        log.info("Total number of employees in the database: {}", total);
//        PageResult pageResult = PageResult.<EmployeeResponseDTO>builder()
//                .total(total)
//                .records(employeeResponseDTOS)
//                .build();
//        log.info("Returning PageResult with total: {} and records size: {}", pageResult.getTotal(), pageResult.getRecords().size());
//
//        return pageResult;

//    /*
//    * !! 基于 PageHelper 插件的分页接口 !!
//    * 流程：
//    * 1. 查询所有员工信息，使用多表查询
//    * 2. 使用 PageHelper 插件自动处理分页
//    * 3. 将 EmployeeResponseDTO 对象列表转换为 PageResult 对象
//    * 4. 返回 PageResult 对象
//    * */
//    @Override
//    public PageResult<EmployeeResponseDTO> selectAllEmployees(Integer page,Integer pageSize){
//        log.info("Fetching all employees with their department names and job titles started.");
//        PageHelper.startPage(page, pageSize); // 使用 PageHelper 插件自动处理分页，注意，这两行代码必须在查询之前调用
//        List<EmployeeResponseDTO> employeeList = employeeMapper.selectAllEmployees(); // 执行原始全部查询操作
//        log.info("Fetched {} employees from the database.", employeeList.size());
//
//        // 使用 PageHelper 插件自动处理分页后，PageHelper 会将查询结果转换为 Page 对象
//        PageInfo<EmployeeResponseDTO> pageInfo = new PageInfo<>(employeeResponseDTOS); // 将 List 转换为 Page 对象
//
//        return PageResult.<EmployeeResponseDTO>builder()
//                .total(pageInfo.getTotal())
//                .records(pageInfo.getList()) // 获取分页后的记录列表
//                .build();
//}

    /*
    * !! 基于 条件查询 + PageHelper 插件的分页接口 !!
    * 流程：
    * 1. 根据条件查询员工信息，使用多表查询
    * 2. 使用 PageHelper 插件自动处理分页
    * 3. 将查询结果转换为 EmployeeResponseDTO 对象
    * 4. 将 EmployeeResponseDTO 对象列表转换为 PageResult 对象
    * 5. 返回 PageResult 对象
    * */
//    @Override
//    public PageResult<EmployeeResponseDTO> selectEmployeesByCondition(String name, Integer gender,
//                                                                      LocalDate startDate, LocalDate endDate,
//                                                                      Integer page, Integer pageSize) {
//        log.info("Fetching employees by condition started.");
//        PageHelper.startPage(page, pageSize); // 使用 PageHelper 插件自动处理分页，注意，这两行代码必须在查询之前调用
//        List<EmployeeResponseDTO> employeeResponseDTOs = employeeMapper.selectEmployeesByCondition(name, gender, startDate, endDate);
//        log.info("Fetched {} employees from the database based on the given conditions.", employeeList.size());
//
//        log.info("Converting EmployeeResponseDTOs to PageInfo.");
//        PageInfo<EmployeeResponseDTO> pageInfo = new PageInfo<>(employeeResponseDTOS); // 将 List 转换为 Page 对象
//        log.info("Returning PageResult with total: {} and records size: {}", pageInfo.getTotal(), pageInfo.getList().size());
//        return PageResult.<EmployeeResponseDTO>builder()
//                .total(pageInfo.getTotal())
//                .records(pageInfo.getList()) // 获取分页后的记录列表
//                .build();
//    }
    @Override
        public PageResult<EmployeeResponseDTO> selectEmployeesByCondition(EmployeeQueryParam employeeQueryParam) {
        log.info("Fetching employees by condition started.");
        PageHelper.startPage(employeeQueryParam.getPage(), employeeQueryParam.getPageSize()); // 使用 PageHelper 插件自动处理分页，注意，这两行代码必须在查询之前调用
        List<EmployeeResponseDTO> employeeResponseDTOS = employeeMapper.selectEmployeesByCondition(employeeQueryParam);
        log.info("Fetched {} employeeDTOs from the database based on the given conditions, detail:{}", employeeResponseDTOS.size(),employeeResponseDTOS);

        log.info("Converting EmployeeResponseDTOs to PageInfo.");
        PageInfo<EmployeeResponseDTO> pageInfo = new PageInfo<>(employeeResponseDTOS); // 将 List 转换为 Page 对象
        log.info("Returning PageResult with total: {} and records size: {}", pageInfo.getTotal(), pageInfo.getList().size());
        return PageResult.<EmployeeResponseDTO>builder()
                .total(pageInfo.getTotal())
                .records(pageInfo.getList()) // 获取分页后的记录列表
                .build();
    }

    /*
    * 流程：
    * 1. 将 EmployeeInsertDTO 转换为 Employee 实体对象
    * 2. 通过mapper.xml定义的useGeneratedKeys属性，获取插入后的主键ID
    * 3. 使用 EmployeeInsertDTO.getEmployeeHistoryInsertDTOList() 获取员工历史信息列表
    * 4. 将 EmployeeHistoryInsertDTOList 中的每一个EmployeeHistoryInsertDTO 转换为 EmployeeHistory 实体对象，并设置 employeeId
    * 5. 批量插入 EmployeeHistory 实体对象到数据库
    * */
    @Transactional // 如果有RuntimeException，则回滚事务,所有插入操作全部回滚
    @Override
    public void insertEmployee(EmployeeInsertDTO employeeInsertDTO) {
        log.info("Inserting new employee started. Department ID: {}, Job ID: {}", employeeInsertDTO.getDepartmentId(), employeeInsertDTO.getJobTitle());

        // 1. 将 EmployeeInsertDTO 转换为 Employee 实体对象
        log.info("Converting new employee started. DTO: {}", employeeInsertDTO);
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeInsertDTO, employee);
        log.info("Converted new employee to entity: {}", employee);

        log.info("Inserting new employee into the database started.");
        employeeMapper.insertEmployee(employee);
        Integer employeeId = employee.getId(); // 获取插入后的主键id
        log.info("Inserted new employee into the database successfully. Employee ID: {}", employeeId);

        log.info("Converting employeeHistoryInsertDTOList to EmployeeHistory, and set employeeId started.");
        List<EmployeeHistoryInsertDTO> employeeHistoryInsertDTOList = employeeInsertDTO.getEmployeeHistoryInsertDTOList();
        if (employeeHistoryInsertDTOList == null || employeeHistoryInsertDTOList.isEmpty()) {
            log.warn("No employee history records to insert for employee ID: {}", employeeId);
            return; // 如果没有历史记录，则直接返回,不执行后续的插入操作
        }
        List<EmployeeHistory> employeeHistorieList = employeeHistoryInsertDTOList.stream().map(employeeHistoryInsertDTO -> {
            EmployeeHistory employeeHistory = new EmployeeHistory();
            BeanUtils.copyProperties(employeeHistoryInsertDTO, employeeHistory);
            employeeHistory.setEmployeeId(employeeId); // 设置 employeeId
            return employeeHistory;
        }).toList();
        log.info("Converted employeeHistoryInsertDTOList to EmployeeHistory, and set employeeId successfully. EmployeeHistoryList: {}", employeeHistorieList);

        log.info("Inserting employeeHistoryList into the database started.");
        employeeHistoryMapper.batchInsertEmployeeHistory(employeeHistorieList);
        log.info("Inserted employeeHistoryList into the database successfully. Size: {}", employeeHistorieList.size());
    }

    /*
    * 流程：
    *  1. 将员工的ID传递到Mapper层的deleteEmployee方法中
    *  2. 在Mapper层中执行删除操作，此时需要注意：需要删除的表是 employee 和 employee_history，
    *  3. 在删除员工信息之前，需要先删除员工头像（OSS），因为删除OSS的数据需要头像的url，如果先删除员工信息，则无法获取头像的url
    *  4. 删除员工信息后，删除员工历史信息
    * */
    @Transactional // 如果有RuntimeException，则回滚事务,所有删除操作全部回滚
    @Override
    public void deleteEmployee(List<Integer> ids) throws ClientException {
        // 首先获取到员工头像的URL列表
        List<String> imageUrls = employeeMapper.getEmployeeImageUrlsByIds(ids);
        if (imageUrls == null || imageUrls.isEmpty()) {
            throw new EmployeeNotFoundException(
                    ErrorCodeEnum.EMPLOYEE_NOT_FOUND.getCode(),
                    ErrorCodeEnum.EMPLOYEE_NOT_FOUND.getMessage());
        }
        log.info("Deleting employee started. IDs: {}, Image URLs: {}", ids, imageUrls);
        // 遍历调用AliyunOssUtil中的删除方法，删除OSS资源
        for (String imageUrl : imageUrls){
            aliyunOssUtil.deleteFile(imageUrl);
            log.info("Deleted OSS image file: {}", imageUrl);
        }
        // 删除员工信息
        log.info("Deleting employee information started. IDs: {}", ids);
        employeeMapper.deleteEmployeeByIds(ids);
        log.info("Deleted employee information successfully. IDs: {}", ids);

        // 删除员工历史信息
        log.info("Deleting employee history information started. IDs: {}", ids);
        employeeHistoryMapper.deleteEmployeeHistoryByEmployeeIds(ids);
        log.info("Deleted employee history information successfully. IDs: {}", ids);
    }

    /*
    * 由于可能存在多个员工的过往经历，所以 EmployeeUpdateDTO 中的 employeeHistoryUpdateDisplayDTOList 是一个 List。
    * 想要实现这个功能，有两种方式：
    * 方法1:
    *  1. 操作多个Mapper，1: EmployeeMapper，2: EmployeeHistoryMapper
    *  2. EmployeeMapper 因为涉及到多表查询，Employee Entity无法覆盖，直接返回 EmployeeDisplayDTO
    *  3. EmployeeHistoryMapper返回 EmployeeHistory Entity实体列表，需要转换为 EmployeeHistoryUpdateDTO 列表
    *  4. 最终将手动将 EmployeeHistoryUpdateDTO 添加进到 EmployeeUpdateDTO 中
    * 方法2:
    *  1. 操作一个Mapper，也就是EmployeeMapper，在其中指定ResultMap 手动映射多表查询结果到 EmployeeUpdateDTO
    *  2. 其中涉及到DTO和子DTO，需要在ResultMap中手动映射多个结果字段到List<子DTO>中
    *  2. 注意：ResultMap这种方式基本上只用于查询操作，不能用于插入、更新、删除等操作，其他的操作还是需要操作两个Mapper
    * */
    @Override
    public EmployeeUpdateDTO selectEmployeeById(Integer id) {
        log.info("Fetching employee by ID: {}", id);
        // 1. 操作 EmployeeMapper 并手动映射结果
        EmployeeUpdateDTO employeeUpdateDTO = employeeMapper.selectEmployeeById(id);
        if (employeeUpdateDTO == null) {
            log.error("Employee not found with ID: {}", id);
            throw new EmployeeNotFoundException(
                    ErrorCodeEnum.EMPLOYEE_NOT_FOUND.getCode(),
                    ErrorCodeEnum.EMPLOYEE_NOT_FOUND.getMessage());
        }
        log.info("Fetched employee by ID: {}, EmployeeDisplayDTO: {}", id, employeeUpdateDTO);
        // 2. 返回结果
        return employeeUpdateDTO;
    }

    /*
    * 流程：
    *  1. 将EmployeeUpdateDTO 转换为 Employee 实体对象,EmployeeHistoryUpdateDisplayDTO集合 转换为 EmployeeHistory 实体对象集合
    *  2. 调用EmployeeMapper的updateEmployee方法更新员工信息，
    *  3. 删除原有的员工历史信息,然后对新的数据进行判空，如果为空，则不执行后续的插入操作，如果不为空，则执行后续的插入操作
    *  4. 调用EmployeeHistoryMapper的updateEmployeeHistory方法插入员工历史信息
    *  5. 更新操作暂时采用先删后加的方式，先删除原有的员工历史信息，再插入新的员工历史信息
    * */
    @Transactional // 如果有RuntimeException，则回滚事务,所有更新操作全部回滚
    @Override
    public void updateEmployee(EmployeeUpdateDTO employeeUpdateDTO) {
        log.info("Updating employee started. DTO: {}", employeeUpdateDTO);

        // 1. 将 EmployeeUpdateDTO 转换为 Employee 实体对象
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeUpdateDTO, employee);
        log.info("Converted employeeUpdateDTO to Employee entity: {}", employee);

        // 2. 更新员工信息
        Integer i = employeeMapper.updateEmployeeById(employee);
        if (i == 0) {
            log.error("Failed to update employee information. Employee ID: {}", employee.getId());
            throw new EmployeeNotFoundException(
                    ErrorCodeEnum.EMPLOYEE_NOT_FOUND.getCode(),
                    ErrorCodeEnum.EMPLOYEE_NOT_FOUND.getMessage());
        }
        log.info("Updated employee information successfully. Employee ID: {}", employee.getId());

        // 3. 删除原有的员工历史信息,并添加上新的员工历史信息
        // 复用 EmployeeHistoryMapper 的 deleteEmployeeHistoryByEmployeeIds 方法，只需要把 employeeId 作为List 传递进去即可
        List<Integer> ids = Collections.singletonList(employee.getId());
        log.info("Deleting old employee history information started. Employee ID: {}", employee.getId());
        employeeHistoryMapper.deleteEmployeeHistoryByEmployeeIds(ids);
        log.info("Deleted old employee history information successfully. Employee ID: {}", employee.getId());

        if (employeeUpdateDTO.getEmployeeHistoryUpdateDTOList() == null || employeeUpdateDTO.getEmployeeHistoryUpdateDTOList().isEmpty()) {
            log.info("No employee history records to update for employee ID: {}", employee.getId());
            // 如果员工没有历史记录，转换过程中的 stream 会抛出 NullPointerException，所以：
            // 如果为空，不需要执行后续的插入的操作,只需要执行了之前的删除操作即可
            return;
        }

        // 4. 判空结束，开始插入操作，将 EmployeeHistoryUpdateDisplayDTO 集合转换为 EmployeeHistory 实体对象集合
        List<EmployeeHistory> employeeHistoryList = employeeUpdateDTO.getEmployeeHistoryUpdateDTOList().stream().map(employeeHistoryUpdateCallbackDTO -> {
            EmployeeHistory employeeHistory = new EmployeeHistory();
            BeanUtils.copyProperties(employeeHistoryUpdateCallbackDTO, employeeHistory);
            return employeeHistory;
        }).toList();
        log.info("Converted employeeHistoryUpdateDisplayDTOList to EmployeeHistory entity list: {}", employeeHistoryList);

        // 5. 补充员工历史信息
        employeeHistoryMapper.batchInsertEmployeeHistory(employeeHistoryList);
        log.info("Inserted new employee history information successfully. Employee ID: {}, History Size: {}", employee.getId(), employeeHistoryList.size());
    }
}
