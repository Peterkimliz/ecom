package com.ecom.ecom.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubcategoryRequest {
    @NotBlank(message = "name is required")
     private String name;
    @NotBlank(message = "categoryId is required")
    private String categoryId;
}
