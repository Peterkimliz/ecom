package com.ecom.ecom.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
   private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Date createdAt;


}
