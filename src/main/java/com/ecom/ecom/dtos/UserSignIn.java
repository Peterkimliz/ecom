package com.ecom.ecom.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignIn {
    @NotBlank(message = "email required")
    private String email;
    @NotBlank(message = "password required")
    private String password;

}
