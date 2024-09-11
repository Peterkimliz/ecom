package com.ecom.ecom.models;

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
@Document(collection = "subcategory")
public class Subcategory {
    private String id;
    private String name;
    @DBRef(lazy = true)
    private Category categoryId;
    
}
