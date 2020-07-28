package com.food.delivery;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.food.delivery.domain.Menu;
import com.food.delivery.domain.Restaurant;
import com.food.delivery.domain.RestaurantRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

@SpringBootApplication
public class OnlineFoodOrderingApp {

    public static void main(String[] args) {
        SpringApplication.run(OnlineFoodOrderingApp.class, args);
    }
}
