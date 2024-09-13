package com.ecom.ecom.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    @NotBlank(message = "name required")
    private String name;
    @NotBlank(message = "description required")
    private String description;
    @NotNull(message = "quantity cannot be null")
    @Positive(message = "quantity must be a positive integer")
    private int quantity;
    @NotNull(message = "price cannot be null")
    @Positive(message = "price must be a positive integer")
    private int price;
    private int offerPrice;
    @NotBlank(message = "category required")
    private String category;
    @NotBlank(message = "subcategory required")
    private String subcategory;
    private String brand;
    private String variantType;
    private List<String> variants ;
    @NotNull(message = "images list cannot be null")
    @Size(min = 1, message = "At least one image URL must be provided")
    private List<String> images;
}
