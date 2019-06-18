package com.example.dplm.spring.db;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dishes")
@Data
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double calories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
    private List<DishIngredients> dishIngredients;

}
