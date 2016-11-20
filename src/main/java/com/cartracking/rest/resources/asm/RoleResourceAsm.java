package com.cartracking.rest.resources.asm;

import com.cartracking.main.entities.Role;
import com.cartracking.rest.controller.RoleController;
import com.cartracking.rest.resources.RoleResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

@Service
public class RoleResourceAsm extends ResourceAssemblerSupport<Role, RoleResource> {

    public RoleResourceAsm() {
        super(RoleController.class, RoleResource.class);
    }

    @Override
    public RoleResource toResource(Role entity) {
        return new RoleResource(entity.getRole(), entity.getId());
    }
}
