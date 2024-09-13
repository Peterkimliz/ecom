package com.ecom.ecom.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ecom.ecom.dtos.ProductRequest;
import com.ecom.ecom.dtos.ProductResponse;
import com.ecom.ecom.dtos.ProductUpdate;
import com.ecom.ecom.services.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/products/")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Validated ProductRequest productRequest) {
        return new ResponseEntity<ProductResponse>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }
   @PutMapping("{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") String id,@RequestBody  ProductUpdate productUpdate) {
        return new ResponseEntity<ProductResponse>(productService.updateProduct(id,productUpdate), HttpStatus.CREATED);
    }
    
    @GetMapping("products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") String id) {
      
        return new ResponseEntity<ProductResponse>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> productResponses = productService.getAllProducts();
        return new ResponseEntity<List<ProductResponse>>(productResponses,
                productResponses.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getFilteredProducts(
            @RequestParam(name = "page",required = false) int page,
            @RequestParam(name = "limit",required = false) int limit,
            @RequestParam(name="categoryId",required = false) String categoryId,
            @RequestParam(name="subcategoryId",required = false) String subCategoryId,
            @RequestParam(name="brandId",required = false) String brandId,
            @RequestParam(name="name",required = false) String name,
            @RequestParam(name="quantity",required = false) String quantity,
            @RequestParam(name="price",required = false) String price ) {
        List<ProductResponse> productResponses = productService.getAllProductsPaginated(page, limit,categoryId,subCategoryId,brandId,name,quantity,price);
        return new ResponseEntity<List<ProductResponse>>(productResponses,
                productResponses.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<String>("Product deleted", HttpStatus.OK);

    }

}
