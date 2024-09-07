package com.ecom.ecom.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecom.ecom.models.UserModel;
import com.ecom.ecom.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsImplementationService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserModel> useOptional = userRepository.findByEmail(username);

        if (useOptional.isPresent()) {
            UserModel userModel = useOptional.get();
            return new User(userModel.getUsername(), userModel.getPassword(), userModel.getAuthorities());
        } else {
            throw new UsernameNotFoundException("User with email address not found");
        }

    }

}
