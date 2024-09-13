package com.ecom.ecom.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.ecom.ecom.dtos.PosterRequest;
import com.ecom.ecom.dtos.PosterResponse;
import com.ecom.ecom.exceptions.ResourceNotFound;
import com.ecom.ecom.models.Poster;
import com.ecom.ecom.repositories.PosterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PosterService {
    private final PosterRepository posterRepository;

    public PosterResponse createPoster(PosterRequest posterRequest) {
        Poster poster = Poster.builder().image(posterRequest.getImage()).name(posterRequest.getName()).build();
        posterRepository.save(poster);
        return PosterResponse.builder().name(poster.getName()).image(poster.getImage()).id(poster.getId())
                .build();
    }

    public List<PosterResponse> getPosters() {
        List<Poster> cOptional = posterRepository.findAll();

        if (cOptional.size() == 0) {
            return new ArrayList<>();
        }
        return cOptional.stream()
                .map(e -> PosterResponse.builder().id(e.getId()).image(e.getImage()).name(e.getName()).build())
                .toList();
    }

    public void deletePoster(String id) {
        Optional<Poster> cOptional = posterRepository.findById(id);

        if (!cOptional.isPresent()) {
            throw new ResourceNotFound("Posters Not Found");
        }

        posterRepository.deleteById(id);
    }

   
}
