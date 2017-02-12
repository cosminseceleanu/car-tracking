package com.cartracking.main.repositories;

import com.cartracking.main.entities.Task;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TaskRepo extends PagingAndSortingRepository<Task, Long> {
    String BY_STATUS_AND_EMPLOYEE = "SELECT t " +
            "FROM Task t " +
            "INNER JOIN t.employee e " +
            "INNER JOIN e.car c " +
            "WHERE e.id = ?1 AND t.status = ?2";

    Page<Task> findByEmployeeAdminId(long adminId, Pageable pageable);

    Page<Task> findByEmployeeId(long employeeId, Pageable pageable);

    @Query(BY_STATUS_AND_EMPLOYEE)
    @Cacheable(cacheNames = "task_by_status_and_employee")
    List<Task> findByStatusAndEmployee(long employeeId, String status);
}
