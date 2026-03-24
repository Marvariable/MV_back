package com.marvariable.marvariable_spring.controller;

import com.marvariable.marvariable_spring.dto.response.PublicationResponseDTO;
import com.marvariable.marvariable_spring.dto.response.VisualArtResponseDTO;
import com.marvariable.marvariable_spring.entity.Publication;
import com.marvariable.marvariable_spring.service.PublicationService;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping
    public List<Publication> getAllPublications(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) LocalDate publicationDate) {
        if (category != null && !category.isBlank()) {
            return publicationService.getPublicationsByCategory(category);
        }

        return publicationService.searchPublications(title, publicationDate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publication> getPublicationById(@PathVariable Long id) {
        Optional<Publication> publication = publicationService.getPublicationById(id);
        return ResponseEntity.of(publication);
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

    @PutMapping("/{id}")
    public Publication updatePublication(@PathVariable Long id, @RequestBody Publication publication) {
        return publicationService.updatePublication(id, publication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id) {
        publicationService.deletePublication(id);
        return ResponseEntity.noContent().build();
    }

    


}