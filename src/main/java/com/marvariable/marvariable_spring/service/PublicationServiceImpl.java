package com.marvariable.marvariable_spring.service;

import com.marvariable.marvariable_spring.dto.response.PublicationResponseDTO;
import com.marvariable.marvariable_spring.dto.response.VisualArtResponseDTO;
import com.marvariable.marvariable_spring.entity.Publication;
import com.marvariable.marvariable_spring.repository.PublicationRepository;


import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    public PublicationServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    @Override
    public List<Publication> getPublicationsByCategory(String category) {
        return publicationRepository.findByCategoryIgnoreCase(category);
    }

    @Override
    public Publication save(Publication publication) {
        Objects.requireNonNull(publication, "La publication no puede ser null");
        return publicationRepository.save(publication);
    }

    @Override
    public Optional<Publication> getPublicationById(Long id) {
        return publicationRepository.findById(id);
    }

    @Override
    public Publication updatePublication(Long id, Publication publication) {
        Publication existingPublication = publicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publication not found with id: " + id));

        existingPublication.setTitle(publication.getTitle());
        existingPublication.setDescription(publication.getDescription());
        existingPublication.setPublicationDate(publication.getPublicationDate());
        existingPublication.setImageUrl(publication.getImageUrl());
        existingPublication.setCategory(publication.getCategory());
        existingPublication.setLink(publication.getLink());
        existingPublication.setStatus(publication.getStatus());

        return publicationRepository.save(existingPublication);
    }

    @Override
    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }

    @Override
    public List<Publication> searchPublications(String title, LocalDate publicationDate) {
        boolean hasTitle = title != null && !title.isBlank();
        boolean hasDate = publicationDate != null;

        if (hasTitle && hasDate) {
            return publicationRepository.findByTitleContainingIgnoreCaseAndPublicationDate(title, publicationDate);
        }

        if (hasTitle) {
            return publicationRepository.findByTitleContainingIgnoreCase(title);
        }

        if (hasDate) {
            return publicationRepository.findByPublicationDate(publicationDate);
        }

        return publicationRepository.findAll();
    }

    @Override
    public List<PublicationResponseDTO> getRecentPublications() {
        return publicationRepository.findAll()
                .stream()
                .map(publication -> new PublicationResponseDTO(
                        publication.getId(),
                        publication.getTitle(),
                        publication.getPublicationDate(),
                        publication.getImageUrl()))
                .toList();
    }

    @Override
    public List<VisualArtResponseDTO> getVisualArts() {
        return publicationRepository.findByCategoryIgnoreCase("Artes visuales")
                .stream()
                .map(publication -> new VisualArtResponseDTO(
                        publication.getId(),
                        publication.getImageUrl(),
                        publication.getPublicationDate(),
                        publication.getCategory()))
                .toList();
    }
}
