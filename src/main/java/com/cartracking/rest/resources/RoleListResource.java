package com.cartracking.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class RoleListResource extends ResourceSupport {
    private List<RoleResource> roles;

    public RoleListResource(List<RoleResource> roles) {
        this.roles = roles;
    }

    public List<RoleResource> getRoles() {
        return roles;
    }
}
