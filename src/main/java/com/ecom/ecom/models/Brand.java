package com.ecom.ecom.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "brand")
public class Brand {
    @Id
    private String id;
    private String name;
    @DBRef(lazy = false)
    private Subcategory subcategory;
    
}
