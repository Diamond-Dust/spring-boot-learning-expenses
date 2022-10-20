package com.spring_boot.learning.expenses.hateoas.assemblers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.spring_boot.learning.expenses.beans.daos.User;
import com.spring_boot.learning.expenses.beans.dtos.UserDTO;
import com.spring_boot.learning.expenses.resources.UserResource;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, UserDTO> {

    @Override
    public UserDTO toModel(User user) {
        UserDTO userDto = new UserDTO(
            user.getId(),
            user.getName(),
            user.getDateOfBirth()
        );

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).getUser(user.getId())).withSelfRel();
        userDto.add(selfLink);
        Link usersLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).getAllUsers()).withRel("users");
        userDto.add(usersLink);

        return userDto;
    }

    @Override
    public CollectionModel<UserDTO> toCollectionModel(Iterable<? extends User> users) {
        List<UserDTO> userDtos = new ArrayList<>();

       for (User user : users) {
            UserDTO userDto = new UserDTO(
                user.getId(),
                user.getName(),
                user.getDateOfBirth()
            );
            userDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).getUser(userDto.getId())).withSelfRel());
            userDtos.add(userDto);
        }

        return CollectionModel.of(userDtos);
    }
    
}
