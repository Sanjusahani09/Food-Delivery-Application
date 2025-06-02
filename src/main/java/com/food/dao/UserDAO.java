package com.food.dao;

import java.util.ArrayList;
import java.util.List;
import com.food.model.User;

public interface UserDAO {
    int insert(User user);
    ArrayList<User> fetchAll();
    User fetchOne(int userId);
    User fetchEmailPassword(String email,String password);
    User fetchEmail(String email);
    void update(int userId, String newPassword);
    void updatePassword(String email,String newPassword);
    void delete(int userId);
}
