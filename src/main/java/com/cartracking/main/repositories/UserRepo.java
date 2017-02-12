package com.cartracking.main.repositories;

import com.cartracking.main.entities.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepo extends CrudRepository<User, Long> {
    String WITHOUT_CAR_QUERY = "SELECT u " +
            "FROM User u LEFT JOIN u.car c " +
            "WHERE u.admin = ?1 AND c.driver IS NULL";

    @Cacheable(cacheNames = "users-by-email")
    User findByEmail(String email);
    Iterable<User> findByAdmin(User admin);

    @Query(WITHOUT_CAR_QUERY)
    Iterable<User> findWithoutCar(User admin);
}
