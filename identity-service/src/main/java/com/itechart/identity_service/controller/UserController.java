package com.itechart.identity_service.controller;

import com.itechart.identity_service.exception.EmailDuplicationException;
import com.itechart.identity_service.model.User;
import com.itechart.identity_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid User user) throws EmailDuplicationException
    {
        return ResponseEntity.ok().body(userService.saveUser(user));
    }
}
