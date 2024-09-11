package com.ecom.ecom.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecom.dtos.CategoryDto;
import com.ecom.ecom.dtos.CategoryResponse;
import com.ecom.ecom.services.CategoryService;

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
@RequestMapping("/api/v1/categories/")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Validated CategoryDto categoryDto) {

        return new ResponseEntity<CategoryResponse>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") String id) {
        categoryService.deletCategory(id);
        return new ResponseEntity<String>("Category Deleted", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categoryResponses = categoryService.getCategories();
        return new ResponseEntity<List<CategoryResponse>>(categoryResponses,
                categoryResponses.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("id") String id,
            @RequestBody CategoryResponse categoryResponse) {

        return new ResponseEntity<CategoryResponse>(categoryService.updateCategory(id, categoryResponse),
                HttpStatus.OK);
    }

}
