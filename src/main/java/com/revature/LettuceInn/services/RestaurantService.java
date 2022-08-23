package com.revature.LettuceInn.services;

import com.revature.LettuceInn.daos.RestaurantDAO;
import com.revature.LettuceInn.models.Restaurant;

import java.util.List;

public class RestaurantService {
    private final RestaurantDAO restoDAO;

    public RestaurantService(RestaurantDAO restoDAO) {
        this.restoDAO = restoDAO;
    }

    public List<Restaurant> getAllRestaurants() {
        return restoDAO.getAll();
    }
}
