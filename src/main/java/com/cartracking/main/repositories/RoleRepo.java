package com.cartracking.main.repositories;

import com.cartracking.main.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleRepo extends CrudRepository<Role, Long> {
    public Role findByRole(String role);
}
