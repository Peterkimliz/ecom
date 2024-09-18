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
import com.ecom.ecom.dtos.PosterRequest;
import com.ecom.ecom.dtos.PosterResponse;
import com.ecom.ecom.services.PosterService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/posters/")
@RequiredArgsConstructor
@Tag(name = "Poster")
public class PosterCategory {
private final PosterService posterService;


@PostMapping()
    public ResponseEntity<PosterResponse> createPoster(@RequestBody @Validated PosterRequest posterRequest) {

        return new ResponseEntity<PosterResponse>(posterService.createPoster(posterRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePoster(@PathVariable("id") String id) {
        posterService.deletePoster(id);
        return new ResponseEntity<String>("Poster Deleted", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PosterResponse>> getAllPosters() {
        List<PosterResponse> posterResponses = posterService.getPosters();
        return new ResponseEntity<List<PosterResponse>>(posterResponses,
                posterResponses.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    
}
