package com.ecom.ecom.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecom.dtos.UserRegistration;
import com.ecom.ecom.dtos.UserResponse;
import com.ecom.ecom.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticatonController {
    private final AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody  @Validated UserRegistration userRegistrationDto) {
        return new ResponseEntity<UserResponse>(authenticationService.registerUser(userRegistrationDto),
                HttpStatus.CREATED);
    }

}
