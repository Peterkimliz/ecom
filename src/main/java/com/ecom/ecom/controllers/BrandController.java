package com.ecom.ecom.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecom.dtos.BrandRequest;
import com.ecom.ecom.dtos.BrandResponse;
import com.ecom.ecom.services.BrandService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/brands/")
@RequiredArgsConstructor
@Tag(name = "Brand")
public class BrandController {
    private final BrandService brandService;

    @PostMapping()
    public ResponseEntity<BrandResponse> createBrand(@RequestBody @Validated BrandRequest brandRequestDto) {
        return new ResponseEntity<BrandResponse>(brandService.createBrand(brandRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSubCategory(@PathVariable("id") String id) {
        brandService.deleteBrand(id);
        return new ResponseEntity<String>("Brand Deleted", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<BrandResponse>> getAllBrannds() {
        List<BrandResponse> brandResponseDtos = brandService.getAllBrands();
        return new ResponseEntity<List<BrandResponse>>(brandResponseDtos,
                brandResponseDtos.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<BrandResponse>> getAllBrandsBySubCategories(@PathVariable("id") String id) {
        List<BrandResponse> brandResponseDtos = brandService.getAllBrandsBySubcategory(id);
        return new ResponseEntity<List<BrandResponse>>(brandResponseDtos,
                brandResponseDtos.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

   

    
}
