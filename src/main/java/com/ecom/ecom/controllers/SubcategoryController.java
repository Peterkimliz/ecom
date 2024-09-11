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

import com.ecom.ecom.dtos.SubCategoryResponse;
import com.ecom.ecom.dtos.SubcategoryRequestDto;
import com.ecom.ecom.services.SubcategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/subcategory/")
@RequiredArgsConstructor
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    @PostMapping()
    public ResponseEntity<SubCategoryResponse> createSubCategory(@RequestBody @Validated SubcategoryRequestDto subcategoryRequestDto) {

        return new ResponseEntity<SubCategoryResponse>(subcategoryService.createSubCategory(subcategoryRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSubCategory(@PathVariable("id") String id) {
        subcategoryService.deletSubCategory(id);
        return new ResponseEntity<String>("subCategory Deleted", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<SubCategoryResponse>> getAllSubCategories() {
        List<SubCategoryResponse> categoryResponses = subcategoryService.getAllSubCategories();
        return new ResponseEntity<List<SubCategoryResponse>>(categoryResponses,
                categoryResponses.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<List<SubCategoryResponse>> getAllSubCategoriesByCategoryId(@PathVariable("id") String id) {
        List<SubCategoryResponse> categoryResponses = subcategoryService.getSubCategoryByCategoryId(id);
        return new ResponseEntity<List<SubCategoryResponse>>(categoryResponses,
                categoryResponses.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

   

    
}
