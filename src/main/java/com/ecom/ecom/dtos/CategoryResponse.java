package com.ecom.ecom.dtos;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CategoryResponse {
     private String id;
    private String name ;
    private String image;
    
}
