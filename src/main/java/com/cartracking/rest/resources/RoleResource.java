package com.cartracking.rest.resources;

import org.springframework.hateoas.ResourceSupport;

public class RoleResource extends ResourceSupport {
    private String role;

    private Long rid;

    public RoleResource(String role, Long rid) {
        this.role = role;
        this.rid = rid;
    }

    public String getRole() {
        return role;
    }

    public Long getRid() {
        return rid;
    }
}
