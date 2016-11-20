package com.cartracking.main.services;

import com.cartracking.main.entities.User;
import com.cartracking.main.services.exceptions.NotFoundException;

import java.util.List;

public interface UserService {
    User find(long id);
    List<User> findAll();
    User findByEmail(String email);
    void delete(long id) throws NotFoundException;
    User create(User user);
}
