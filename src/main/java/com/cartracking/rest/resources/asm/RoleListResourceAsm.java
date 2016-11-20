package com.cartracking.rest.resources.asm;

import com.cartracking.main.entities.Role;
import com.cartracking.rest.controller.RoleController;
import com.cartracking.rest.resources.RoleListResource;
import com.cartracking.rest.resources.RoleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleListResourceAsm extends ResourceAssemblerSupport<List<Role>, RoleListResource> {

    private RoleResourceAsm roleResourceAsm;

    @Autowired
    public RoleListResourceAsm(RoleResourceAsm roleResourceAsm) {
        super(RoleController.class, RoleListResource.class);
        this.roleResourceAsm = roleResourceAsm;
    }

    @Override
    public RoleListResource toResource(List<Role> roles) {
        List<RoleResource> resourceList = roleResourceAsm.toResources(roles);

        return new RoleListResource(resourceList);
    }
}
