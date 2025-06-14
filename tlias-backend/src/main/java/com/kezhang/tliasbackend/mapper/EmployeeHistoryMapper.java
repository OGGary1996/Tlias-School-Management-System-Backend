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
}
