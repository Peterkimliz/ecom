package com.ecom.ecom.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.ecom.models.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {


   @Query("{'name': { $regex: ?0, $options: 'i' }, 'category.id': ?1 }")
    Page<Product> findByNameAndCategoryAndSubcategoryAndBrandId(String name, String categoryId, String subCategoryId, String brandId, Pageable pageable);

    @Query("{'category.id': ?0}")
    Page<Product>findByCategory(String categoryId, Pageable pageable);

    @Query("{'name': { $regex: ?0 $options: 'i' } }")
    Page<Product>findByName(String name, Pageable pageable);
    
    @Query("{'subcategory.id': ?0 }")
    Page<Product>findBySubcategory(String subCategory, Pageable pageable);
     
    @Query("{'brand.id': ?0}")
    Page<Product>findByBrand( String brandId, Pageable pageable);

    @Query("{'name': { $regex: ?0 $options: 'i' }, 'category.id': ?1 }")
    Page<Product> findByNameAndCategory(String name, String categoryId, Pageable pageable);


    @Query("{'name': { $regex: ?0 $options: 'i' }, 'subcategory.id': ?1 }")
    Page<Product> findByNameAndSubcategory(String name, String subCategory, Pageable pageable);

   

}
