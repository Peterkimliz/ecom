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

import com.ecom.ecom.dtos.BrandRequestDto;
import com.ecom.ecom.dtos.BrandResponseDto;
import com.ecom.ecom.services.BrandService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/brands/")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping()
    public ResponseEntity<BrandResponseDto> createBrand(@RequestBody @Validated BrandRequestDto brandRequestDto) {
        return new ResponseEntity<BrandResponseDto>(brandService.createBrand(brandRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSubCategory(@PathVariable("id") String id) {
        brandService.deleteBrand(id);
        return new ResponseEntity<String>("Brand Deleted", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<BrandResponseDto>> getAllBrannds() {
        List<BrandResponseDto> brandResponseDtos = brandService.getAllBrands();
        return new ResponseEntity<List<BrandResponseDto>>(brandResponseDtos,
                brandResponseDtos.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<BrandResponseDto>> getAllBrandsBySubCategories(@PathVariable("id") String id) {
        List<BrandResponseDto> brandResponseDtos = brandService.getAllBrandsBySubcategory(id);
        return new ResponseEntity<List<BrandResponseDto>>(brandResponseDtos,
                brandResponseDtos.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

   

    
}
