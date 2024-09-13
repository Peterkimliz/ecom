package com.ecom.ecom.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ecom.ecom.dtos.BrandResponse;
import com.ecom.ecom.dtos.CategoryResponse;
import com.ecom.ecom.dtos.ProductRequest;
import com.ecom.ecom.dtos.ProductResponse;
import com.ecom.ecom.dtos.ProductUpdate;
import com.ecom.ecom.dtos.SubCategoryResponse;
import com.ecom.ecom.dtos.VariantTypeResponse;
import com.ecom.ecom.exceptions.ResourceNotFound;
import com.ecom.ecom.models.Brand;
import com.ecom.ecom.models.Category;
import com.ecom.ecom.models.Product;
import com.ecom.ecom.models.Subcategory;
import com.ecom.ecom.models.Variant;
import com.ecom.ecom.models.VariantType;
import com.ecom.ecom.repositories.BrandRepository;
import com.ecom.ecom.repositories.CategoryRepository;
import com.ecom.ecom.repositories.ProductRepository;
import com.ecom.ecom.repositories.SubcategoryRepository;
import com.ecom.ecom.repositories.VariantRepository;
import com.ecom.ecom.repositories.VariantTypesRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final VariantTypesRepository variantTypesRepository;
    private final VariantRepository variantRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {

        Optional<Category> category = categoryRepository.findById(productRequest.getCategory());
        if (!category.isPresent()) {
            throw new ResourceNotFound("Category not found");
        }
        Optional<Brand> brand = brandRepository.findById(productRequest.getBrand());
        if (!brand.isPresent()) {
            throw new ResourceNotFound("Brand not found");
        }
        Optional<Subcategory> subcategory = subcategoryRepository.findById(productRequest.getSubcategory());
        if (!subcategory.isPresent()) {
            throw new ResourceNotFound("Subcategory not found");
        }
        Optional<VariantType> variantType = variantTypesRepository.findById(productRequest.getVariantType());
        if (!variantType.isPresent()) {
            throw new ResourceNotFound("VariantType  not found");
        }

        Product product = Product
                .builder()
                .createdAt(new Date(System.currentTimeMillis()))
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .images(productRequest.getImages())
                .offerPrice(productRequest.getOfferPrice())
                .category(category.get())
                .brand(brand.get())
                .subcategory(subcategory.get())
                .variantType(variantType.get())
                .variants(getVariants(productRequest.getVariants()))
                .build();

        productRepository.save(product);
        return createProductResponse(product);

    }

    public ProductResponse updateProduct(String id, ProductUpdate productUpdate) {
        Optional<Product> pOptional = productRepository.findById(id);
        if (!pOptional.isPresent()) {
            throw new ResourceNotFound("Product with id doesnot exist");
        }
        Product foundProduct = pOptional.get();
        Product product = Product
                .builder()
                .id(foundProduct.getId())
                .name(productUpdate.getName() == null ? foundProduct.getName() : productUpdate.getName())
                .price(productUpdate.getPrice() == null ? foundProduct.getPrice()
                        : Integer.parseInt(productUpdate.getPrice()))
                .quantity(productUpdate.getQuantity() == null ? foundProduct.getQuantity()
                        : Integer.parseInt(productUpdate.getQuantity()))
                .offerPrice(productUpdate.getOfferPrice() == null ? foundProduct.getOfferPrice()
                        : Integer.parseInt(productUpdate.getOfferPrice()))
                .images(productUpdate.getImages().isEmpty() ? foundProduct.getImages() : productUpdate.getImages())

                .build();
        productRepository.save(product);
        return createProductResponse(product);

    }

    public ProductResponse getProductById(String id) {
        Optional<Product> pOptional = productRepository.findById(id);
        if (!pOptional.isPresent()) {
            throw new ResourceNotFound("Product not found");
        }
        return createProductResponse(pOptional.get());
    }

    public void deleteProduct(String id) {
        Optional<Product> pOptional = productRepository.findById(id);
        if (!pOptional.isPresent()) {
            throw new ResourceNotFound("Product not found");
        }
        productRepository.deleteById(id);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.size() == 0) {
            return new ArrayList<>();
        }
        return products.stream().map(e -> createProductResponse(e)).toList();
    }

    public List<ProductResponse> getAllProductsPaginated(int page, int limit, String categoryId, String subCategoryId,
            String brandId, String name, String quantity, String price) {

        List<Product> products = productRepository.findAll();
        if (products.size() == 0) {
            return new ArrayList<>();
        }
        return products.stream().map(e -> createProductResponse(e)).toList();
    }

    private List<Variant> getVariants(List<String> list) {
        List<Variant> variants = variantRepository.findAllById(list);
        return variants;
    }

    private ProductResponse createProductResponse(Product product) {
        ProductResponse productResponse = ProductResponse
                .builder()
                .name(product.getName())
                .id(product.getId())
                .price(product.getPrice())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .createdAt(product.getCreatedAt())
                .offerPrice(product.getOfferPrice())
                .images(product.getImages())
                .category(createCategoryResponse(product.getCategory()))
                .subcategory(createSubCategoryResponse(product.getSubcategory()))
                .brand(createBrand(product.getBrand()))
                .variantType(createVariantType(product.getVariantType()))
                .build();
        return productResponse;
    }

    private VariantTypeResponse createVariantType(VariantType variantType) {
        return VariantTypeResponse.builder().id(variantType.getId()).name(variantType.getName())
                .type(variantType.getType()).build();
    }

    private BrandResponse createBrand(Brand brand) {
        return BrandResponse
                .builder()
                .id(brand.getId())
                .name(brand.getName())
                .subcategory(createSubCategoryResponse(brand.getSubcategory()))
                .build();
    }

    private SubCategoryResponse createSubCategoryResponse(Subcategory subcategory) {
        return SubCategoryResponse
                .builder()
                .id(subcategory.getId())
                .category(createCategoryResponse(subcategory.getCategoryId()))
                .name(subcategory.getName())
                .build();

    }

    private CategoryResponse createCategoryResponse(Category category) {
        return CategoryResponse.builder().id(category.getId()).name(category.getName()).image(category.getImage())
                .build();
    }

}
