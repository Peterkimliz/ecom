package com.ecom.ecom.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VariantTypeRequest {
  @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "type is required")
    private String type;
    
}