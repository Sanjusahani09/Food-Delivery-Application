package com.food.dao;

import java.util.List;
import com.food.model.Menu;

public interface MenuDAO {
    void insert(Menu menu);
    List<Menu> fetchAll();
    Menu fetchOne(int menuId);
    List<Menu> fetchMenuByRestaurantId(int restaurantId);
    void update(int menuId, String newDescription, int newPrice, boolean isAvailable);
    void delete(int menuId);
}
