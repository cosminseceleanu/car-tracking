package com.cartracking.rest.controller;

import com.cartracking.main.entities.User;
import com.cartracking.main.services.EmployeeService;
import com.cartracking.rest.resources.UserListResource;
import com.cartracking.rest.resources.UserResource;
import com.cartracking.rest.resources.asm.UserListResourceAsm;
import com.cartracking.rest.resources.asm.UserResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
@Secured("ROLE_ADMIN")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserResourceAsm userResourceAsm;

    @Autowired
    UserListResourceAsm userListResourceAsm;

    @RequestMapping(value = "/{userId}/employees", method = RequestMethod.GET)
    public ResponseEntity<UserListResource> findAll(@PathVariable long userId, @RequestParam(value = "filter", defaultValue = "") String filter) {
        List<User> users;
        if (filter != null && filter.equals("without-car")) {
            users = employeeService.findWithoutCar(userId);
        } else {
            users = employeeService.findAll(userId);
        }

        return new ResponseEntity<>(userListResourceAsm.toResource(users), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/employees", method = RequestMethod.POST)
    public ResponseEntity<UserResource> create(@PathVariable long userId, @RequestBody UserResource userResource) {
        User user = employeeService.create(userResource.toUser(), userId);

        return new ResponseEntity<>(userResourceAsm.toResource(user), HttpStatus.CREATED);
    }
}
