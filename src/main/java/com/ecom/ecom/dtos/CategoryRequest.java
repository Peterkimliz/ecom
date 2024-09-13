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
public class CategoryRequest {
    @NotBlank(message = "name required")
    private String name ;
    @NotBlank(message = "image required")
    private String image;
}
