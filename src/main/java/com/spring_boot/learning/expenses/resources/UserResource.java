package com.spring_boot.learning.expenses.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring_boot.learning.expenses.beans.daos.User;
import com.spring_boot.learning.expenses.beans.dtos.UserDTO;
import com.spring_boot.learning.expenses.exceptions.UserNotFoundException;
import com.spring_boot.learning.expenses.hateoas.assemblers.UserModelAssembler;
import com.spring_boot.learning.expenses.services.UserDAOService;


@RestController
@RequestMapping("/services-resources")
public class UserResource {
    @Autowired
    private UserDAOService userService;
    @Autowired
    private UserModelAssembler userModelAssembler;

    @GetMapping("/users")
    public CollectionModel<UserDTO> getAllUsers() {
        List<User> users = userService.getUsers();
        CollectionModel<UserDTO> userDtos = userModelAssembler.toCollectionModel(users);
        return userDtos;
    }

    @GetMapping("/user/{id}")
    public UserDTO getUser(@PathVariable int id) {
        User foundUser = userService.getUser(id);
        if (foundUser == null) {
            throw new UserNotFoundException("id: " + id);
        }
        UserDTO userDto = userModelAssembler.toModel(foundUser);
        return userDto;
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/user/{id}")
    public UserDTO updateUser(@PathVariable int id, @Valid @RequestBody User user) {
        user.setId(id);
        User putUser = userService.updateUser(user);
        UserDTO userDto = userModelAssembler.toModel(putUser);
        return userDto;
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        User deletedUser = userService.deleteUser(id);
        if (deletedUser == null) {
            throw new UserNotFoundException("id: " + id);
        }
    }
}
