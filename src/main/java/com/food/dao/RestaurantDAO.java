package com.food.dao;

import java.util.List;
import com.food.model.Restaurant;

public interface RestaurantDAO {
    void insert(Restaurant res);
    List<Restaurant> fetchAll();
    Restaurant fetchOne(int restaurantId);
    void update(int restaurantId, String newAddress, float newRatings);
    void delete(int restaurantId);
    List<Restaurant> searchRestaurants(String query);
}

