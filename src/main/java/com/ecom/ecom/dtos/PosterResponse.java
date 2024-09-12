package com.ecom.ecom.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PosterResponse {
    private String id;
    private String name ;
    private String image;    
    
}
