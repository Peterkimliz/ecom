package com.ecom.ecom.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPageResponse {
    Integer pageNumber;
    Integer pageSize;
    Integer totalPages;
    long totalElements;
    List<ProductResponse> data;

}
