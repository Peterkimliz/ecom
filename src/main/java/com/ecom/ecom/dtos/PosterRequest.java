package com.ecom.ecom.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PosterRequest {
    @NotBlank(message = "name is required")
    private String name ;
    @NotBlank(message = "image is required")
    private String image;    
    
}
