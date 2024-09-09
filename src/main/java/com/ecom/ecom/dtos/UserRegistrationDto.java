package com.ecom.ecom.dtos;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationDto {
    @NotBlank(message = "firstName required")
    private String firstName;
    @NotBlank(message = "lastName required")
    private String lastName;
    @NotBlank(message = "phone required")
    private String phone;
    @NotBlank(message = "email required")
    private String email;
    @NotBlank(message = "password required")
    private String password;
}
