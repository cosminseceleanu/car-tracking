package com.cartracking.main.services.impl;

import com.cartracking.main.common.ListConverter;
import com.cartracking.main.entities.Role;
import com.cartracking.main.entities.User;
import com.cartracking.main.repositories.UserRepo;
import com.cartracking.main.services.EmployeeService;
import com.cartracking.main.services.RoleService;
import com.cartracking.main.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private UserRepo userRepo;

    private RoleService roleService;

    @Autowired
    public EmployeeServiceImpl(UserRepo userRepo, RoleService roleService) {
        this.userRepo = userRepo;
        this.roleService = roleService;
    }

    @Override
    public List<User> findAll(long adminId) {
        User admin = userRepo.findOne(adminId);
        Iterable<User> users = userRepo.findByAdmin(admin);
        ListConverter<User> listConverter = new ListConverter<>(users);

        return listConverter.convert();
    }

    @Override
    public User create(User employee, long adminId) throws NotFoundException {
        setAdmin(employee, adminId);
        setupRole(employee);

        return userRepo.save(employee);
    }

    @Override
    public List<User> findWithoutCar(long adminId) {
        Iterable<User> users = userRepo.findWithoutCar(userRepo.findOne(adminId));

        return new ListConverter<>(users).convert();
    }

    private void setAdmin(User employee, long adminId) throws NotFoundException {
        User admin = userRepo.findOne(adminId);
        if (admin == null) {
            throw new NotFoundException();
        }
        employee.setAdmin(admin);
    }

    private void setupRole(User user) {
        Role role = roleService.findByRole(Role.ROLE_USER);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRoles(roleSet);
    }
}
