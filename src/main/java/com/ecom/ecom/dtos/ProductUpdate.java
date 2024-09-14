package com.ecom.ecom.dtos;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdate {
    private String name;
    private String description;
    private String quantity;
    private String price;
    private String offerPrice;
    @Builder.Default
    private List<String> images=new ArrayList<>();
}
