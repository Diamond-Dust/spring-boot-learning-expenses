package com.spring_boot.learning.expenses.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring_boot.learning.expenses.beans.User;
import com.spring_boot.learning.expenses.services.UserDAOService;

@RestController
@RequestMapping("/services-resources")
public class UserResource {
    @Autowired
    private UserDAOService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        return userService.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    public User deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
