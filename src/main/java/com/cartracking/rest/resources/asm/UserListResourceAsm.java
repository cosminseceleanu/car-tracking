package com.cartracking.rest.resources.asm;


import com.cartracking.main.entities.User;
import com.cartracking.rest.controller.UserController;
import com.cartracking.rest.resources.UserListResource;
import com.cartracking.rest.resources.UserResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserListResourceAsm extends ResourceAssemblerSupport<List<User>, UserListResource> {
    public UserListResourceAsm() {
        super(UserController.class, UserListResource.class);
    }

    @Override
    public UserListResource toResource(List<User> users) {
        List<UserResource> resourceList = new UserResourceAsm().toResources(users);
        UserListResource resource = new UserListResource();
        resource.setUsers(resourceList);

        return resource;
    }
}
