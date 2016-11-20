package com.cartracking.main.services;


import com.cartracking.main.entities.Role;

import java.util.List;

public interface RoleService {
    public List<Role> findAll();
    public Role findByRole(String role);
}
