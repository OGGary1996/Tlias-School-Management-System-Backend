package com.kezhang.tliasbackend.mapper;

import com.kezhang.tliasbackend.entity.EmployeeHistory;

import java.util.List;

public interface EmployeeHistoryMapper {

    /*
    * Insert more than one employee history record into the database.
    * This method adds multiple employee history records in a single batch operation.
    * @param employeeHistoryList List of EmployeeHistory objects to be inserted
    * */
    void batchInsertEmployeeHistory(List<EmployeeHistory> employeeHistoryList);

    // 删除员工信息的操作，隶属于删除操作的一部分，另一部分在 EmployeeMapper 中实现
    /*
    * Delete employee history records by their employee IDs.
    * This method removes all history records associated with the specified employee IDs.
    * @param ids List of employee IDs whose history records are to be deleted
    * */
    void deleteEmployeeHistoryByEmployeeIds(List<Integer> employeeIds);
}
