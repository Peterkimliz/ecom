package com.ecom.ecom.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.ecom.dtos.UserRegistrationDto;
import com.ecom.ecom.dtos.UserResponseDto;
import com.ecom.ecom.exceptions.ResourceExists;
import com.ecom.ecom.models.Role;
import com.ecom.ecom.models.UserModel;
import com.ecom.ecom.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto registerUser(UserRegistrationDto userRequestDto) {
        System.out.println("hello");
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
        return UserResponseDto.builder().createdAt(userModel.getCreatedAt()).email(userModel.getEmail())
                .firstName(userModel.getFirstName()).lastName(userModel.getLastName()).id(userModel.getId())
                .phone(userModel.getPhone()).build();

    }

}
