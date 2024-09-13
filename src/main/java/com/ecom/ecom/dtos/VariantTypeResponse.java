package com.ecom.ecom.dtos;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VariantTypeResponse {
   private String id;
    private String name;
    private String type;
    
}