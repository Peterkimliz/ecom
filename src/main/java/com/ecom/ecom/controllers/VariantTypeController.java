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

import com.ecom.ecom.dtos.VariantTypeRequest;
import com.ecom.ecom.dtos.VariantTypeResponse;
import com.ecom.ecom.services.VariantTypeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/varianttypes/")
@RequiredArgsConstructor
@Tag(name = "VariantType")
public class VariantTypeController {
    private final VariantTypeService variantTypeService;

@PostMapping()
    public ResponseEntity<VariantTypeResponse> createVariantType(@RequestBody @Validated VariantTypeRequest variantTypeRequest) {
        return new ResponseEntity<VariantTypeResponse>(variantTypeService.createVariantType(variantTypeRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVariantType(@PathVariable("id") String id) {
        variantTypeService.deletVariant(id);
        return new ResponseEntity<String>("VariantType Deleted", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<VariantTypeResponse>> getAllVariantTypes() {
        List<VariantTypeResponse> posterResponses = variantTypeService.getVariantsType();
        return new ResponseEntity<List<VariantTypeResponse>>(posterResponses,
                posterResponses.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
    
}
