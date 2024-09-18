package com.ecom.ecom.services;

import java.util.Date;
import java.util.Optional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.ecom.dtos.AuthenticationResponse;
import com.ecom.ecom.dtos.UserRegistration;
import com.ecom.ecom.dtos.UserResponse;
import com.ecom.ecom.dtos.UserSignIn;
import com.ecom.ecom.exceptions.ResourceExists;
import com.ecom.ecom.exceptions.ResourceNotFound;
import com.ecom.ecom.models.Role;
import com.ecom.ecom.models.UserModel;
import com.ecom.ecom.repositories.UserRepository;
import com.ecom.ecom.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserResponse registerUser(UserRegistration userRequestDto) {

        Optional<UserModel> uOptional = userRepository.findByEmail(userRequestDto.getEmail());
        System.out.println(uOptional);
        if (uOptional.isPresent()) {

            throw new ResourceExists("User with email address already exists");
        }
        UserModel userModel = UserModel
                .builder()
                .createdAt(new Date(System.currentTimeMillis()))
                .email(userRequestDto.getEmail())
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .phone(userRequestDto.getPhone())
                .role(Role.USER)
                .build();
        userRepository.save(userModel);
        return UserResponse.builder().createdAt(userModel.getCreatedAt()).email(userModel.getEmail())
                .firstName(userModel.getFirstName()).lastName(userModel.getLastName()).id(userModel.getId())
                .phone(userModel.getPhone()).build();

    }

    public AuthenticationResponse loginUser(UserSignIn userSignIn) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userSignIn.getEmail(), userSignIn.getPassword()));
        } catch (Exception e) {
            throw new ResourceNotFound("invalid email or password");
        }
        Optional<UserModel> uUuserModel = userRepository.findByEmail(userSignIn.getEmail());
        UserModel userModel = uUuserModel.get();

        UserResponse userResponse = UserResponse.builder().createdAt(userModel.getCreatedAt())
                .email(userModel.getEmail())
                .firstName(userModel.getFirstName()).lastName(userModel.getLastName()).id(userModel.getId())
                .phone(userModel.getPhone()).build();

        String token = jwtService.generateToken(userModel);
        return AuthenticationResponse.builder().data(userResponse).token(token).build();

    }

}
