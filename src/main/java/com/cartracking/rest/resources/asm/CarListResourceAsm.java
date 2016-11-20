package com.cartracking.rest.resources.asm;

import com.cartracking.main.entities.Car;
import com.cartracking.rest.controller.CarController;
import com.cartracking.rest.resources.CarListResource;
import com.cartracking.rest.resources.CarResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarListResourceAsm extends ResourceAssemblerSupport<List<Car>, CarListResource> {
    private CarResourceAsm carResourceAsm;

    @Autowired
    public CarListResourceAsm(CarResourceAsm carResourceAsm) {
        super(CarController.class, CarListResource.class);
        this.carResourceAsm = carResourceAsm;
    }

    @Override
    public CarListResource toResource(List<Car> cars) {
        List<CarResource> resourcesList = carResourceAsm.toResources(cars);
        CarListResource carListResource = new CarListResource();
        carListResource.setCarResourceList(resourcesList);

        return carListResource;
    }
}
