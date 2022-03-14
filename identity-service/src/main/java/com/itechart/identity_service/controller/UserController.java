package com.itechart.identity_service.controller;

import com.itechart.identity_service.model.User;
import com.itechart.identity_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/identity")
public class  UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<User> registerUser(@RequestBody User user){
            return ResponseEntity.ok().body(userService.saveUser(user));
    }
}
