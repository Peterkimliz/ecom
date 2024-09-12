package com.ecom.ecom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ecom.ecom.dtos.CategoryResponse;
import com.ecom.ecom.dtos.SubCategoryResponse;
import com.ecom.ecom.dtos.SubcategoryRequestDto;
import com.ecom.ecom.exceptions.ResourceExists;
import com.ecom.ecom.exceptions.ResourceNotFound;
import com.ecom.ecom.models.Category;
import com.ecom.ecom.models.Subcategory;
import com.ecom.ecom.repositories.CategoryRepository;
import com.ecom.ecom.repositories.SubcategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubcategoryService {
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    public SubCategoryResponse createSubCategory(SubcategoryRequestDto subcategoryRequestDto) {
        Optional<Subcategory> cOptional = subcategoryRepository
                .findByName(subcategoryRequestDto.getName().toLowerCase());
        Optional<Category> category = categoryRepository.findById(subcategoryRequestDto.getCategoryId());

        if (cOptional.isPresent()) {
            throw new ResourceExists("subCategory already exists");
        }
        if (!category.isPresent()) {
            throw new ResourceNotFound("Category doesnot exists");
        }

        Subcategory subcategory = Subcategory.builder().categoryId(category.get()).name(subcategoryRequestDto.getName().toLowerCase())
                .build();
        subcategoryRepository.save(subcategory);

        return SubCategoryResponse.builder().name(subcategory.getName()).id(subcategory.getId())
                .category(CategoryResponse.builder().name(category.get().getName()).id(category.get().getId())
                        .image(category.get().getImage()).build())
                .build();
    }

    public List<SubCategoryResponse> getAllSubCategories() {
        List<Subcategory> cOptional = subcategoryRepository.findAll();

        if (cOptional.size() == 0) {
            return new ArrayList<>();
        }
        return cOptional.stream()
                .map(e -> SubCategoryResponse.builder().id(e.getId())
                        .category(CategoryResponse.builder().id(e.getCategoryId().getId())
                                .image(e.getCategoryId().getImage()).name(e.getCategoryId().getName()).build())
                        .name(e.getName()).build())
                .toList();
    }
    public List<SubCategoryResponse> getSubCategoryByCategoryId(String id) {
        System.out.println("testing");
        List<Subcategory> cOptional = subcategoryRepository.findByCategoryId(id);

        if (cOptional.size() == 0) {
            return new ArrayList<>();
        }
        return cOptional.stream()
                .map(e -> SubCategoryResponse.builder().id(e.getId())
                        .category(CategoryResponse.builder().id(e.getCategoryId().getId())
                                .image(e.getCategoryId().getImage()).name(e.getCategoryId().getName()).build())
                        .name(e.getName()).build())
                .toList();
    }

    public void deletSubCategory(String subcategoryId) {
        Optional<Subcategory> cOptional = subcategoryRepository.findById(subcategoryId);

        if (!cOptional.isPresent()) {
            throw new ResourceNotFound("subCategory Not Found");
        }

        subcategoryRepository.deleteById(subcategoryId);
    }
    
}
