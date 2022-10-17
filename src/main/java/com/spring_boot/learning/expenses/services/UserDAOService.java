package com.spring_boot.learning.expenses.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spring_boot.learning.expenses.beans.User;

@Component
public class UserDAOService {
    private static int nextIdToUse = 0;
    private static ArrayList<User> users = new ArrayList<User>();

    public User addUser(User user) {
        if (user.getId() == null)
            user.setId(nextIdToUse++);

        boolean success = users.add(user);
        
        return user;
    }

    public User getUser(int id) {
        User user = null;
        Iterator<User> usersIterator = users.iterator();
        while  (usersIterator.hasNext()) {
            user = usersIterator.next();
            if (user.getId() == id) {
                break;
            }
        }
        return user;
    }

    public List<User> getUsers() {
        return users;
    }

    public User updateUser(User user) {
        if (user.getId() == null)
            return null;

        User oldUser = null;
        Iterator<User> usersIterator = users.iterator();
        while  (usersIterator.hasNext()) {
            oldUser = usersIterator.next();
            if (oldUser.getId() == user.getId()) {
                break;
            }
        }
        if (oldUser != null)
            users.remove(oldUser);
        users.add(user);
        return user;
    }

    public User deleteUser(int id) {
        User user = null;
        Iterator<User> usersIterator = users.iterator();
        while  (usersIterator.hasNext()) {
            user = usersIterator.next();
            if (user.getId() == id) {
                usersIterator.remove();
                break;
            }
        }
        return user;
    }
}
