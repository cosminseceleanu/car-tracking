package com.cartracking.rest.controller;

import com.cartracking.main.entities.Car;
import com.cartracking.main.services.CarService;
import com.cartracking.rest.exceptions.NotFoundException;
import com.cartracking.rest.resources.CarListResource;
import com.cartracking.rest.resources.CarResource;
import com.cartracking.rest.resources.asm.CarListResourceAsm;
import com.cartracking.rest.resources.asm.CarResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarResourceAsm resourceAsm;

    @Autowired
    private CarListResourceAsm listResourceAsm;

    @RequestMapping(value = "/users/{userId}/cars", method = RequestMethod.GET)
    public ResponseEntity<CarListResource> findAll(@PathVariable long userId) {
        List<Car> cars = carService.findAll(userId);

        return new ResponseEntity<>(listResourceAsm.toResource(cars), HttpStatus.OK);
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
    public ResponseEntity<CarResource> find(@PathVariable long id) {
        Car car = carService.find(id);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resourceAsm.toResource(car), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/cars", method = RequestMethod.POST)
    public ResponseEntity<CarResource> create(@PathVariable long userId, @RequestBody CarResource carResource) {
        Car car = carResource.toCar();
        if (carResource.getEmployee() != null) {
            car = carService.assignToDriver(car, carResource.getEmployee().toUser());
        }
        car = carService.create(car, userId);
        CarResource newCarResource = resourceAsm.toResource(car);

        return new ResponseEntity<>(newCarResource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{userId}/cars/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CarResource> update(@PathVariable long userId, @PathVariable long id,
                                              @RequestBody CarResource carResource) {
        Car car = carService.find(id);
        if (car == null) {
            throw new NotFoundException();
        }
        Car updatedCar = carResource.toCar();
        if (carResource.getEmployee() != null) {
            updatedCar.setDriver(carResource.getEmployee().toUser());
        }
        updatedCar = carService.update(updatedCar, userId);

        return new ResponseEntity<>(resourceAsm.toResource(updatedCar), HttpStatus.OK);
    }
}
