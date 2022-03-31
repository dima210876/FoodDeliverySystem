package com.itechart.identity_service.controller;

import com.itechart.identity_service.exception.EmailDuplicationException;
import com.itechart.identity_service.exception.RegistrationConfirmationTokenException;
import com.itechart.identity_service.model.LoginData;
import com.itechart.identity_service.model.User;
import com.itechart.identity_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/confirm/{confirmationToken}")
    public ResponseEntity<String> confirmUserRegistration(@PathVariable(value = "confirmationToken") String confirmationToken)
            throws RegistrationConfirmationTokenException
    {
        return ResponseEntity.ok().body(userService.confirmUserRegistration(confirmationToken));
    }
}
