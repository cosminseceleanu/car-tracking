package com.cartracking.rest.controller;

import com.cartracking.main.entities.Alert;
import com.cartracking.main.services.AlertService;
import com.cartracking.rest.resources.AlertResource;
import com.cartracking.rest.resources.asm.AlertResourceAsm;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @Autowired
    private AlertResourceAsm resourceAsm;

    @Autowired
    private PagedResourcesAssembler<Alert> pagedResourcesAssembler;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/alerts/search", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<AlertResource>> search(@QuerydslPredicate(root = Alert.class) Predicate predicate, Pageable pageable) {
        Page<Alert> alerts = alertService.search(predicate, pageable);
        PagedResources<AlertResource> resources = pagedResourcesAssembler.toResource(alerts, resourceAsm);

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
}
