package com.ecom.ecom.dtos;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VariantResponse {
   private String id;
    private String name;
    private VariantTypeResponse variantType;
    
}