package com.cartracking.main.services.impl;

import com.cartracking.main.common.ListConverter;
import com.cartracking.main.entities.Role;
import com.cartracking.main.repositories.RoleRepo;
import com.cartracking.main.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> findAll() {
        Iterable<Role> roles = roleRepo.findAll();

        return new ListConverter<Role>(roles).convert();
    }

    @Override
    public Role findByRole(String role) {
        return roleRepo.findByRole(role);
    }
}
