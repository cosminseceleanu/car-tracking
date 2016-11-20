package com.cartracking.main.repositories;

import com.cartracking.main.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepo extends CrudRepository<User, Long> {
    public final static String WITHOUT_CAR_QUERY = "SELECT u " +
            "FROM User u LEFT JOIN u.car c " +
            "WHERE u.admin = ?1 AND c.driver IS NULL";

    User findByEmail(String email);
    Iterable<User> findByAdmin(User admin);
    @Query(WITHOUT_CAR_QUERY)
    Iterable<User> findWithoutCar(User admin);
}
