package com.example.dplm.spring.db;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_statistic")
public class UserStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;
    private Double weigh;
    private Long timestamp;

}
