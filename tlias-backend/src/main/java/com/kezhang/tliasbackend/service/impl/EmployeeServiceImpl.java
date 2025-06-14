package com.kezhang.tliasbackend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.dto.EmployeeHistoryInsertDTO;
import com.kezhang.tliasbackend.dto.EmployeeInsertDTO;
import com.kezhang.tliasbackend.dto.EmployeeQueryParam;
import com.kezhang.tliasbackend.dto.EmployeeResponseDTO;
import com.kezhang.tliasbackend.entity.Employee;
import com.kezhang.tliasbackend.entity.EmployeeHistory;
import com.kezhang.tliasbackend.mapper.EmployeeHistoryMapper;
import com.kezhang.tliasbackend.mapper.EmployeeMapper;
import com.kezhang.tliasbackend.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final EmployeeHistoryMapper employeeHistoryMapper;
    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper, EmployeeHistoryMapper employeeHistoryMapper) {
        this.employeeMapper = employeeMapper;
        this.employeeHistoryMapper = employeeHistoryMapper;
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
    * 2. 插入 Employee 实体对象到数据库
    * 3. 通过mapper.xml定义的useGeneratedKeys属性，获取插入后的主键ID
    * *     注意：EmployeeInsertDTO 中有一个子 DTO 的 employeeHistoryInsertDTO，
    * 4. 使用 EmployeeInsertDTO.getEmployeeHistoryInsertDTOList() 获取员工历史信息列表
    * 5. 将 EmployeeHistoryInsertDTOList 中的每一个EmployeeHistoryInsertDTO 转换为 EmployeeHistory 实体对象，并设置 employeeId
    * 6. 批量插入 EmployeeHistory 实体对象到数据库
    * */
    @Transactional // 如果有RuntimeException，则回滚事务,所有插入操作全部回滚
    @Override
    public void insertEmployee(EmployeeInsertDTO employeeInsertDTO) {
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
}
