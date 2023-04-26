package com.augusto.soloveganbusiness.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augusto.soloveganbusiness.dto.UserDto;
import com.augusto.soloveganbusiness.models.User;
import com.augusto.soloveganbusiness.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class HomeController {
    private final UserService userService;

    @GetMapping("/greeting")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hola Spring Security");
    }

    @GetMapping("/goodbye")
    public ResponseEntity<String> sayGoodBye() {
        return ResponseEntity.ok("Chao Spring Security");
    }

    @PostMapping("/create/users")
    public User createUser(@RequestBody UserDto userDto) {
        User user = userService.registerUser(userDto);
        return user;
    }
}
