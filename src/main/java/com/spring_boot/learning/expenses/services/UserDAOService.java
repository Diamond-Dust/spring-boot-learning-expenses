package com.spring_boot.learning.expenses.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spring_boot.learning.expenses.beans.User;

@Component
public class UserDAOService {
    private static ArrayList<User> users = new ArrayList<User>();

    public User addUser(User user) {
        if (user.getId() == null)
            user.setId(users.size());

        boolean success = users.add(user);
        
        return user;
    }

    public User getUser(int id) {
        if (id > users.size()) {
            return null;
        }
        return users.get(id);
    }

    public List<User> getUsers() {
        return users;
    }

    public User updateUser(User user) {
        User oldUser = users.get(user.getId());
        if (oldUser != null)
            users.remove(oldUser);
        users.add(user);
        return user;
    }

    public User deleteUser(int id) {
        User oldUser = users.get(id);
        if (oldUser != null)
            users.remove(oldUser);
        return oldUser;
    }
}
