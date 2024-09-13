package com.ecom.ecom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ecom.ecom.dtos.VariantTypeRequest;
import com.ecom.ecom.dtos.VariantTypeResponse;
import com.ecom.ecom.exceptions.ResourceExists;
import com.ecom.ecom.exceptions.ResourceNotFound;
import com.ecom.ecom.models.VariantType;
import com.ecom.ecom.repositories.VariantTypesRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class VariantTypeService {
  private final  VariantTypesRepository variantTypesRepository;

    public VariantTypeResponse createVariantType(VariantTypeRequest variantTypeRequest) {
        Optional<VariantType> cOptional = variantTypesRepository.findByType(variantTypeRequest.getType().toLowerCase());
        if (cOptional.isPresent()) {
            throw new ResourceExists("VariantType already exists");
        }
        VariantType variantType = VariantType.builder()
                .name(variantTypeRequest.getName().toLowerCase()).type(variantTypeRequest.getType()).build();
                System.out.println(variantType);
                variantTypesRepository.save(variantType);
        return VariantTypeResponse.builder().name(variantType.getName()).type(variantType.getType()).id(variantType.getId()).build();
    }

    public List<VariantTypeResponse> getVariantsType() {
        List<VariantType> cOptional = variantTypesRepository.findAll();

        if (cOptional.size() == 0) {
            return new ArrayList<>();
        }
        return cOptional.stream()
                .map(e -> VariantTypeResponse.builder().id(e.getId()).name(e.getName()).type(e.getType()).build())
                .toList();
    }

    public void deletVariant(String categoryId) {
        Optional<VariantType> cOptional = variantTypesRepository.findById(categoryId);

        if (!cOptional.isPresent()) {
            throw new ResourceNotFound("Variant Not Found");
        }

        variantTypesRepository.deleteById(categoryId);
    }
    
}
