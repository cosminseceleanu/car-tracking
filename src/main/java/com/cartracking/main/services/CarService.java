package com.cartracking.main.services;

import com.cartracking.main.entities.Car;
import com.cartracking.main.entities.User;

import java.util.List;

public interface CarService {
    Car find(Long id);
    Car create(Car car, long userId);
    Car update(Car car, long userId);
    List<Car> findAll(long userId);
    Car assignToDriver(Car car, User user);
}
