package com.cartracking.rest.controller;

import com.cartracking.main.entities.User;
import com.cartracking.main.services.UserService;
import com.cartracking.main.services.exceptions.NotFoundException;
import com.cartracking.rest.resources.UserListResource;
import com.cartracking.rest.resources.UserResource;
import com.cartracking.rest.resources.asm.UserListResourceAsm;
import com.cartracking.rest.resources.asm.UserResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserResourceAsm resourceAsm;

    @Autowired
    private UserListResourceAsm listResourceAsm;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody UserListResource findAll() {
        return listResourceAsm.toResource(userService.findAll());
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserResource> find (@PathVariable long userId) {
        User user = userService.find(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resourceAsm.toResource(user), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResource> create(@RequestBody UserResource sentUserResource) {
        User user = userService.create(sentUserResource.toUser());
        UserResource userResource = resourceAsm.toResource(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(userResource.getLink("self").getHref()));

        return new ResponseEntity<>(userResource, httpHeaders, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public ResponseEntity<UserResource> delete(@PathVariable long userId) {
        try {
            userService.delete(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
