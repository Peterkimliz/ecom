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
import com.ecom.ecom.dtos.VariantRequest;
import com.ecom.ecom.dtos.VariantResponse;
import com.ecom.ecom.services.VariantService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/variants/")

@RequiredArgsConstructor
@Tag(name = "Variant")
public class VariantController {
    private final VariantService variantService;

    @PostMapping()
    public ResponseEntity<VariantResponse> createVariant(@RequestBody @Validated VariantRequest variantRequest) {

        return new ResponseEntity<VariantResponse>(variantService.createVariant(variantRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVariant(@PathVariable("id") String id) {
        variantService.deleteVariant(id);
        return new ResponseEntity<String>("Variant Deleted", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<VariantResponse>> getAllVariants() {
        List<VariantResponse> posterResponses = variantService.getAllVatiants();
        return new ResponseEntity<List<VariantResponse>>(posterResponses,
                posterResponses.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<VariantResponse>> getAllVariantsByVariantTypeId(@PathVariable("id") String id) {
        List<VariantResponse> posterResponses = variantService.getAllVatiantsByVariantTypeId(id);
        return new ResponseEntity<List<VariantResponse>>(posterResponses,
                posterResponses.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

}
