package com.ecom.ecom.models;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class UserModel  implements UserDetails{

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private Role role;
    private Date createdAt;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       
      return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {
       return email;
    }


    

}
