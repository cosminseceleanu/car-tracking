package com.cartracking.rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.cartracking.main.entities.User;
import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {
    private String name;
    private String email;
    private String password;
    private long rid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRid(long id) {
        this.rid = id;
    }

    public long getRid() {
        return rid;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser() {
        User user = new User();
        user.setId(rid);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);

        return user;
    }
}
