package com.cartracking.main.services.impl;

import com.cartracking.main.entities.Car;
import com.cartracking.main.entities.User;
import com.cartracking.main.repositories.CarRepo;
import com.cartracking.main.services.CarService;
import com.cartracking.main.services.UserService;
import com.cartracking.rest.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private CarRepo carRepo;
    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CarServiceImpl(CarRepo carRepo, UserService userService) {
        this.carRepo = carRepo;
        this.userService = userService;
    }

    @Override
    public Car find(Long id) {
        return carRepo.findOne(id);
    }

    @Override
    public Car create(Car car, long userId) {
        User user = userService.find(userId);
        if (user == null) {
            throw new NotFoundException();
        }
        car.setOwner(user);

        return carRepo.save(car);
    }

    @Override
    public Car assignToDriver(Car car, User user) {
        car.setDriver(entityManager.getReference(User.class, user.getId()));

        return car;
    }

    @Override
    public List<Car> findAll(long userId) {
        List<Car> carList = new ArrayList<>();
        Iterable<Car> cars = carRepo.findAll();
        for (Car car : cars) {
            carList.add(car);
        }

        return carList;
    }

    @Override
    @Transactional
    public Car update(Car car, long userId) {
        car.setOwner(userService.find(userId));
        car = entityManager.merge(car);
        car = carRepo.save(car);

        return car;
    }
}
