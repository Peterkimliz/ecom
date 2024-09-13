package com.ecom.ecom.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom.ecom.dtos.CategoryRequest;
import com.ecom.ecom.dtos.CategoryResponse;
import com.ecom.ecom.exceptions.ResourceExists;
import com.ecom.ecom.exceptions.ResourceNotFound;
import com.ecom.ecom.models.Category;
import com.ecom.ecom.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CategoryRequest categoryDto) {
        Optional<Category> cOptional = categoryRepository.findByName(categoryDto.getName().toLowerCase());

        if (cOptional.isPresent()) {
            throw new ResourceExists("Category already exists");
        }
        Category category = Category.builder().createdAt(new Date(System.currentTimeMillis()))
                .name(categoryDto.getName().toLowerCase()).image(categoryDto.getImage()).build();
        categoryRepository.save(category);
        return CategoryResponse.builder().name(category.getName()).image(category.getImage()).id(category.getId())
                .build();
    }

    public List<CategoryResponse> getCategories() {
        List<Category> cOptional = categoryRepository.findAll();

        if (cOptional.size() == 0) {
            return new ArrayList<>();
        }
        return cOptional.stream()
                .map(e -> CategoryResponse.builder().id(e.getId()).image(e.getImage()).name(e.getName()).build())
                .toList();
    }

    public void deletCategory(String categoryId) {
        Optional<Category> cOptional = categoryRepository.findById(categoryId);

        if (!cOptional.isPresent()) {
            throw new ResourceNotFound("Category Not Found");
        }

        categoryRepository.deleteById(categoryId);
    }

    public CategoryResponse updateCategory(String categoryId, CategoryResponse categoryResponse) {
        Optional<Category> cOptional = categoryRepository.findById(categoryId);

        if (!cOptional.isPresent()) {
            throw new ResourceNotFound("Category Not Found");
        }
        Category category = Category.builder()
                .name(categoryResponse.getName() == null ? cOptional.get().getName() : categoryResponse.getName())
                .image(categoryResponse.getImage() == null ? cOptional.get().getImage() : categoryResponse.getImage())
                .id(categoryId)
                .build();
        categoryRepository.save(category);
        return CategoryResponse.builder().id(category.getId()).image(category.getImage()).name(category.getName())
                .build();
    }

}
