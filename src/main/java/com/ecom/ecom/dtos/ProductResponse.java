package com.ecom.ecom.dtos;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private int quantity;
    private int price;
    private int offerPrice;
    private CategoryResponse  category;
    private SubCategoryResponse subcategory;
    @Builder.Default
    private BrandResponse brand=null;
    @Builder.Default
    private VariantTypeResponse variantType=null;
    private List<VariantResponse> variants;
    private List<String> images;
    private Date createdAt;
}
