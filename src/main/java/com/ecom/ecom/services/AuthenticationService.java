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
        Optional<UserModel> uOptional = userRepository.findByEmail(userRequestDto.getEmail());
        if (uOptional.isPresent()) {
            System.out.println("user already exists");
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
        return UserResponseDto.builder().build();

    }

}
