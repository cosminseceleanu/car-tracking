package com.cartracking.rest.resources.asm;

import com.cartracking.main.entities.Alert;
import com.cartracking.rest.controller.AlertController;
import com.cartracking.rest.resources.AlertResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

@Service
public class AlertResourceAsm extends ResourceAssemblerSupport<Alert, AlertResource> {

    public AlertResourceAsm() {
        super(AlertController.class, AlertResource.class);
    }

    @Override
    public AlertResource toResource(Alert entity) {
        AlertResource resource = new AlertResource();
        resource.setRid(entity.getId());
        resource.setDate(entity.getDate());
        resource.setTitle(entity.getTitle());
        resource.setMessage(entity.getMessage());

        return resource;
    }
}
