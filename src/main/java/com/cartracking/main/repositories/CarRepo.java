package com.cartracking.main.repositories;

import com.cartracking.main.entities.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CarRepo extends CrudRepository<Car, Long> {
    public final static String FIND_ALL_WITH_EMPLOYEES_QUERY = "SELECT c " +
            "FROM Car c LEFT JOIN c.driver d";

    @Query(FIND_ALL_WITH_EMPLOYEES_QUERY)
    public Iterable<Car> findAll();
}
