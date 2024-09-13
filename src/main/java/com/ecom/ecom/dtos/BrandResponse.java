package com.ecom.ecom.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandResponse {
    private String id;
    private String name;
    private SubCategoryResponse subcategory;
}