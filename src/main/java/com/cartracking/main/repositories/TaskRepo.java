package com.cartracking.main.repositories;

import com.cartracking.main.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepo extends PagingAndSortingRepository<Task, Long> {
    Page<Task> findByEmployeeAdminId(long adminId, Pageable pageable);

    Page<Task> findByEmployeeId(long employeeId, Pageable pageable);
}
