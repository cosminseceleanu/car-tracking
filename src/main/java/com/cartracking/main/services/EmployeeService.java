package com.cartracking.main.services;

import com.cartracking.main.entities.User;
import com.cartracking.main.services.exceptions.NotFoundException;

import java.util.List;

public interface EmployeeService {
    List<User> findAll(long adminId);
    User create(User employee, long adminId) throws NotFoundException;
    List<User> findWithoutCar(long adminId);
}
