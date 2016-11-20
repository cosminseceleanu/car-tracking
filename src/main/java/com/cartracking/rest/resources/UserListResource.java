package com.cartracking.rest.resources;


import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class UserListResource extends ResourceSupport {
    private List<UserResource> users = new ArrayList<>();

    public List<UserResource> getUsers() {
        return users;
    }

    public void setUsers(List<UserResource> users) {
        this.users = users;
    }
}
