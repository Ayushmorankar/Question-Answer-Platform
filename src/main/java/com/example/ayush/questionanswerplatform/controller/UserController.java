package com.example.ayush.questionanswerplatform.controller;

import com.example.ayush.questionanswerplatform.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(){
        userService.createUser();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
