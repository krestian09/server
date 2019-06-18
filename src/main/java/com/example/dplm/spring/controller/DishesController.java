package com.example.dplm.spring.controller;

import com.example.dplm.spring.db.Dish;
import com.example.dplm.spring.repos.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DishesController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private DishRepository dishRepository;


    @RequestMapping("/dishes")
    public Iterable<Dish> getDishes(){
        return dishRepository.findAll();
    }

    @GetMapping("/dishes")
    public Iterable<Dish> getDishesByPart(@RequestParam(value = "part", defaultValue = "") String part){
        List<Dish> dishes = new ArrayList<Dish>();
        for (Dish dish: dishRepository.findAll()) {
            if(dish.getName().toUpperCase().contains(part.toUpperCase())) dishes.add(dish);
        }
        return dishes;
    }

//    @RequestMapping("/ingredients")
//    public Iterable<Ingredient> getIngredients() {
//        return ingredientRepository.findAll();
//    }
}
