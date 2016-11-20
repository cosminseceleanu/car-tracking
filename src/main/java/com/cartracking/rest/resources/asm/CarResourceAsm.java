package com.cartracking.rest.resources.asm;

import com.cartracking.main.entities.Car;
import com.cartracking.rest.controller.CarController;
import com.cartracking.rest.resources.CarResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

@Service
public class CarResourceAsm extends ResourceAssemblerSupport<Car, CarResource> {

    private UserResourceAsm userResourceAsm;

    @Autowired
    public CarResourceAsm(UserResourceAsm userResourceAsm) {
        super(CarController.class, CarResource.class);
        this.userResourceAsm = userResourceAsm;
    }

    @Override
    public CarResource toResource(Car car) {
        CarResource resource = new CarResource();
        resource.setBrand(car.getBrand());
        resource.setModel(car.getModel());
        resource.setNumber(car.getNumber());
        resource.setRid(car.getId());
        if (car.getDriver() != null) {
            resource.setEmployee(userResourceAsm.toResource(car.getDriver()));
        }

        return resource;
    }
}
