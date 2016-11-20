package com.cartracking.rest.controller;

import com.cartracking.main.entities.Role;
import com.cartracking.main.services.RoleService;
import com.cartracking.rest.resources.RoleListResource;
import com.cartracking.rest.resources.asm.RoleListResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleListResourceAsm roleListResourceAsm;

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<RoleListResource> findAll() {
        List<Role> roles = roleService.findAll();

        return new ResponseEntity<>(roleListResourceAsm.toResource(roles), HttpStatus.OK);
    }
}
