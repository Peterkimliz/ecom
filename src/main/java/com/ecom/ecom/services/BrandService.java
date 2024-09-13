package com.ecom.ecom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom.ecom.dtos.BrandRequest;
import com.ecom.ecom.dtos.BrandResponse;
import com.ecom.ecom.dtos.CategoryResponse;
import com.ecom.ecom.dtos.SubCategoryResponse;
import com.ecom.ecom.exceptions.ResourceExists;
import com.ecom.ecom.exceptions.ResourceNotFound;
import com.ecom.ecom.models.Brand;
import com.ecom.ecom.models.Category;
import com.ecom.ecom.models.Subcategory;
import com.ecom.ecom.repositories.BrandRepository;
import com.ecom.ecom.repositories.SubcategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final SubcategoryRepository subcategoryRepository;

    public BrandResponse createBrand(BrandRequest brandRequestDto) {
        Optional<Subcategory> sOptional = subcategoryRepository.findById(brandRequestDto.getSubcategoryId());
        Optional<Brand> bOptional = brandRepository.findByName(brandRequestDto.getName().toLowerCase());

        if (bOptional.isPresent()) {
            throw new ResourceExists("Brand already exist");
        }
        if (!sOptional.isPresent()) {
            throw new ResourceExists("Subcategory  doesnot  exist");
        }
        Brand brand = Brand.builder().name(brandRequestDto.getName().toLowerCase()).subcategory(sOptional.get())
                .build();
        brandRepository.save(brand);
        Subcategory subcategory = sOptional.get();

        return BrandResponse.builder().id(brand.getId()).name(brand.getName())
                .subcategory(createSubResponse(subcategory))
                .build();

    }

    public void deleteBrand(String id) {
        Optional<Brand> bOptional = brandRepository.findById(id);

        if (!bOptional.isPresent()) {
            throw new ResourceNotFound("Brand doesnot exist");
        }
        brandRepository.deleteById(id);
    }

    public List<BrandResponse> getAllBrands() {
        List<Brand> bList = brandRepository.findAll();
        if (bList.size() == 0) {
            return new ArrayList<>();
        }

        List<BrandResponse> brandResponseDtos = mapBrandToBrandResponseDto(bList);
        return brandResponseDtos;

}

    public List<BrandResponse> getAllBrandsBySubcategory(String id) {

        List<Brand> bList = brandRepository.findBySubcategory(id);
        if (bList.size() == 0) {
            return new ArrayList<>();
        }
        List<BrandResponse> brandResponseDtos = mapBrandToBrandResponseDto(bList);
        return brandResponseDtos;
    }

    private List<BrandResponse> mapBrandToBrandResponseDto(List<Brand> bList) {

        return bList.stream().map(
                e -> BrandResponse
                        .builder()
                        .id(e.getId())
                        .name(e.getName())
                        .subcategory(createSubResponse(e.getSubcategory()))
                        .build())
                .toList();

    }

    private CategoryResponse createCategoryResponse(Category e) {
        return CategoryResponse.builder()
                .id(e.getId())
                .name(e.getName())
                .image(e.getImage())
                .build();

    }

    private SubCategoryResponse createSubResponse(Subcategory e) {
        return SubCategoryResponse

                .builder()
                .id(e.getId())
                .name(e.getName())
                .category(createCategoryResponse(e.getCategoryId()))
                .build();

    }

}
