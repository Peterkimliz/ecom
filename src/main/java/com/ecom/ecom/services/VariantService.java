package com.ecom.ecom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom.ecom.dtos.VariantRequest;
import com.ecom.ecom.dtos.VariantResponse;
import com.ecom.ecom.dtos.VariantTypeResponse;
import com.ecom.ecom.exceptions.ResourceNotFound;
import com.ecom.ecom.models.Variant;
import com.ecom.ecom.models.VariantType;
import com.ecom.ecom.repositories.VariantRepository;
import com.ecom.ecom.repositories.VariantTypesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VariantService {
    private final VariantRepository variantRepository;
    private final VariantTypesRepository variantTypesRepository;

    public VariantResponse createVariant(VariantRequest variantRequest) {
        Optional<VariantType> vOptional = variantTypesRepository.findById(variantRequest.getVariantTypeId());
        Optional<Variant> vOptional2 = variantRepository.findByName(variantRequest.getName());

        if (!vOptional.isPresent()) {
            throw new ResourceNotFound("Variant Type doesnot exist");
        }
        if (vOptional2.isPresent()) {
            throw new ResourceNotFound("Variant  already exist");
        }
        Variant variant = Variant.builder().name(variantRequest.getName()).variantType(vOptional.get()).build();
        variantRepository.save(variant);

        return VariantResponse.builder()
                .name(variant.getName())
                .id(variant.getId())
                .variantType(createVariantTypeResponse(variant.getVariantType()))
                .build();

    }

    public List<VariantResponse> getAllVatiants() {
        List<Variant> variant = variantRepository.findAll();
        if (variant.size() == 0) {
            return new ArrayList<>();
        }

        return createVariantResponse(variant);
    }

    public List<VariantResponse> getAllVatiantsByVariantTypeId(String id) {
        List<Variant> variant = variantRepository.findByVariantType(id);
        if (variant.size() == 0) {
            return new ArrayList<>();
        }

        return createVariantResponse(variant);
    }

    public void deleteVariant(String id) {

        Optional<Variant> vOptional = variantRepository.findById(id);
        if (!vOptional.isPresent()) {
            throw new ResourceNotFound("Variant  doesnot exist");
        }
        variantRepository.deleteById(id);

    }

    private List<VariantResponse> createVariantResponse(List<Variant> variant) {
        return variant
                .stream()
                .map(e -> VariantResponse.builder().name(e.getName()).id(e.getId())
                        .variantType(createVariantTypeResponse(e.getVariantType())).build())
                .toList();

    }

    private VariantTypeResponse createVariantTypeResponse(VariantType variantType) {
        return VariantTypeResponse.builder().id(variantType.getId()).name(variantType.getName())
                .type(variantType.getType()).build();
    }

}
