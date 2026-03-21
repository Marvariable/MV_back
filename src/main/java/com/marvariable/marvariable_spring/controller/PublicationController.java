package com.marvariable.marvariable_spring.controller;

import com.marvariable.marvariable_spring.dto.response.PublicationResponseDTO;
import com.marvariable.marvariable_spring.dto.response.VisualArtResponseDTO;
import com.marvariable.marvariable_spring.entity.Publication;
import com.marvariable.marvariable_spring.service.PublicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping
    public List<Publication> getAllPublications(@RequestParam(required = false) String category) {
        if (category != null && !category.isBlank()) {
            return publicationService.getPublicationsByCategory(category);
        }
        return publicationService.getAllPublications();
    }

    @GetMapping("/recent")
    public List<PublicationResponseDTO> getRecentPublications() {
        return publicationService.getRecentPublications();

    }

    @GetMapping("/visual-arts")
    public List<VisualArtResponseDTO> getVisualArts() {
        return publicationService.getVisualArts();
    }

    @PostMapping
    public Publication createPublication(@RequestBody Publication publication) {
        return publicationService.save(publication);
    }
}