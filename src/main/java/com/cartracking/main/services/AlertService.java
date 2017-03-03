package com.cartracking.main.services;

import com.cartracking.main.entities.Alert;
import com.cartracking.main.entities.User;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlertService {
    void add(String title, String message, User user);

    Page<Alert> search(Predicate predicate, Pageable pageable);
}
