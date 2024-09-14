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
import com.ecom.ecom.dtos.VariantResponse;
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
        Optional<Subcategory> subcategory = subcategoryRepository.findById(productRequest.getSubcategory());
        if (!subcategory.isPresent()) {
            throw new ResourceNotFound("Subcategory not found");
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
                .subcategory(subcategory.get())
                .brand(findBrand(productRequest.getBrand()))
                .variantType(getVariantType(productRequest.getVariantType()))
                .variants(getVariants(productRequest.getVariants()))
                .build();

        productRepository.save(product);
        ProductResponse productResponse = createProductResponse(product);

        return productResponse;

    }

    public ProductResponse updateProduct(String id, ProductUpdate productUpdate) {

        Optional<Product> pOptional = productRepository.findById(id);

        if (!pOptional.isPresent()) {
            throw new ResourceNotFound("Product with id doesnot exist");
        }
        Product foundProduct = pOptional.get();
        System.out.println(foundProduct);

        Product product = Product
                .builder()
                .id(foundProduct.getId())
                .category(foundProduct.getCategory())
                .subcategory(foundProduct.getSubcategory())
                .name(productUpdate.getName() == null ? foundProduct.getName() : productUpdate.getName())
                .price(productUpdate.getPrice() == null ? foundProduct.getPrice()
                        : Integer.parseInt(productUpdate.getPrice()))
                .quantity(productUpdate.getQuantity() == null ? foundProduct.getQuantity()
                        : Integer.parseInt(productUpdate.getQuantity()))
                .offerPrice(productUpdate.getOfferPrice() == null ? foundProduct.getOfferPrice()
                        : Integer.parseInt(productUpdate.getOfferPrice()))
                .images(productUpdate.getImages().size()==0 ? foundProduct.getImages() : productUpdate.getImages())
                // .variantType(foundProduct.getVariantType())
                // .brand(foundProduct.getBrand())
                // .variants(foundProduct.getVariants())
                .description(productUpdate.getDescription()==null?foundProduct.getDescription():productUpdate.getDescription())

                .build();
                System.out.println(product);
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

    private VariantType getVariantType(String variantType) {

        if (variantType.isBlank()) {
            return null;
        }

        Optional<VariantType> ovariantType = variantTypesRepository.findById(variantType);
        if (!ovariantType.isPresent()) {
            throw new ResourceNotFound("VariantType  not found");
        }
        return ovariantType.get();

    }

    private Brand findBrand(String brand) {

        if (brand.isBlank()) {
            return null;
        }
        Optional<Brand> obrand = brandRepository.findById(brand);
        if (!obrand.isPresent()) {
            throw new ResourceNotFound("Brand not found");
        }
        return obrand.get();
    }

    private List<Variant> getVariants(List<String> list) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        List<Variant> variants = variantRepository.findAllById(list);
        return variants;
    }

    private ProductResponse createProductResponse(Product product) {
        boolean brandIsEmpty=product.getBrand()==null;
        boolean variantIsEmpty=product.getVariantType()==null;
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
                .brand(createBrand(brandIsEmpty==true?Brand.builder().build():product.getBrand()))
                .variantType(createVariantType(variantIsEmpty==true?VariantType.builder().build():product.getVariantType()))
                .variants(createVariants(product.getVariants()))
                .build();
               
        
        return productResponse;
    }

    private List<VariantResponse> createVariants(List<Variant> variants) {
        if (variants.size() == 0) {
            return new ArrayList<>();
        }
        return variants.stream().map(e -> VariantResponse.builder().id(e.getId()).name(e.getName())
                .variantType(createVariantType(e.getVariantType())).build()).toList();

    }

    private VariantTypeResponse createVariantType(VariantType variantType) {
        if (variantType.getId() != null) {
            return VariantTypeResponse.builder().id(variantType.getId()).name(variantType.getName())
                    .type(variantType.getType()).build();
        }
        return null;

    }

    private BrandResponse createBrand(Brand brand) {

        if (brand.getId() != null) {
            return BrandResponse
                    .builder()
                    .id(brand.getId())
                    .name(brand.getName())
                    .subcategory(createSubCategoryResponse(brand.getSubcategory()))
                    .build();
        }

        return null;
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
