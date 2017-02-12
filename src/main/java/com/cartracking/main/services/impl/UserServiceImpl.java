package com.cartracking.main.services.impl;

import com.cartracking.main.entities.User;
import com.cartracking.main.repositories.UserRepo;
import com.cartracking.main.services.UserService;
import com.cartracking.main.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User find(long id) {
        return userRepo.findOne(id);
    }

    @Override
    public List<User> findAll() {
        List<User> usersList = new ArrayList<User>();
        Iterable<User> users = userRepo.findAll();
        for (User user : users) {
            usersList.add(user);
        }

        return usersList;
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Transactional
    @CacheEvict(cacheNames = "users-by-email", allEntries = true)
    @Override
    public void delete(long id) throws NotFoundException {
        User user = find(id);
        if (user == null) {
            throw new NotFoundException();
        }
        userRepo.delete(user);
    }

    @Transactional
    @Override
    public User create(User user) {
        return userRepo.save(user);
    }
}
