package com.spring_boot.learning.expenses.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring_boot.learning.expenses.beans.MessageBean;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-bean")
    public MessageBean helloWorldBean() {
        return new MessageBean("Hello World");
    }

    @GetMapping("/hello-path/{name}")
    public MessageBean helloPathBean(@PathVariable("name") String nameInPath) {
        return new MessageBean(String.format("Hello, %s", nameInPath));
    }
}
