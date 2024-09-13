package com.ecom.ecom.models;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
@Builder
public class Product {
    private String id;
    private String name;
    private String description;
    private int quantity;
    private int price;
    private int offerPrice;
    @DBRef(lazy = true)
    private Category category;
    @DBRef(lazy = true)
    private Subcategory subcategory;
    @DBRef(lazy = true)
    private Brand brand;
    @DBRef(lazy = true)
    private VariantType variantType;
    @DBRef(lazy = true)
    @Builder.Default
    private List<Variant> variants = new ArrayList<>();
    @Builder.Default
    private List<String> images = new ArrayList<>();
    private Date createdAt;

}
