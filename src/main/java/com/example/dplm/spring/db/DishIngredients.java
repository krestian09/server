package com.example.dplm.spring.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "dish_ingredients")
@Data
public class DishIngredients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "dish_id")
    @JsonIgnore
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "dish_ingredient_id")
    private Dish dishIngredient;

    private double weight;
}
