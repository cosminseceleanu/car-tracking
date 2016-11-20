package com.cartracking.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class CarListResource extends ResourceSupport {
    private List<CarResource> carResourceList;

    public List<CarResource> getCarResourceList() {
        return carResourceList;
    }

    public void setCarResourceList(List<CarResource> carResourceList) {
        this.carResourceList = carResourceList;
    }
}
