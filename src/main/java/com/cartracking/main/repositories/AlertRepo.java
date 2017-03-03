package com.cartracking.main.repositories;

import com.cartracking.main.entities.Alert;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlertRepo extends PagingAndSortingRepository<Alert, Long>, QueryDslPredicateExecutor<Alert> {
}
