package com.example.dplm.spring.repos;

import com.example.dplm.spring.db.Dish;
import org.springframework.data.repository.CrudRepository;

public interface DishRepository extends CrudRepository<Dish, Long> {
}
