package com.ecom.ecom.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VariantRequest {
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "variantTypeId is required")
    private String variantTypeId;
    
}