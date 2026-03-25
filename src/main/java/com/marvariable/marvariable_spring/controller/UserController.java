package com.marvariable.marvariable_spring.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.marvariable.marvariable_spring.entity.UserDetail;
import com.marvariable.marvariable_spring.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDetail> register(@RequestBody @Valid UserDetail user) {
        UserDetail createdUser = userService.register(user);
        createdUser.setPassword(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}