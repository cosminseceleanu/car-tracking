package com.cartracking.rest.resources.asm;

import com.cartracking.main.entities.User;
import com.cartracking.rest.controller.UserController;
import com.cartracking.rest.resources.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Service
public class UserResourceAsm extends ResourceAssemblerSupport <User, UserResource> {
    public UserResourceAsm() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User entity) {
        UserResource res = new UserResource();
        res.setEmail(entity.getEmail());
        res.setName(entity.getName());
        res.setRid(entity.getId());
        res.setPassword(entity.getPassword());
        res.setAdminId(entity.getAdmin().getId());
        Link link = linkTo(methodOn(UserController.class).find(entity.getId())).withSelfRel();
        res.add(link.withSelfRel());

        return res;
    }
}
