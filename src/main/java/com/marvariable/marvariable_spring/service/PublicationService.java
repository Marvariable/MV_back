package com.marvariable.marvariable_spring.service;

import com.marvariable.marvariable_spring.dto.response.PublicationResponseDTO;
import com.marvariable.marvariable_spring.dto.response.VisualArtResponseDTO;
import com.marvariable.marvariable_spring.entity.Publication;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PublicationService {

    List<Publication> getAllPublications();

    List<Publication> getPublicationsByCategory(String category);

    Publication save(Publication publication);

    Optional<Publication> getPublicationById(Long id);

    Publication updatePublication(Long id, Publication publication);

    void deletePublication(Long id);

    List<Publication> searchPublications(String title, LocalDate publicationDate);

    List<PublicationResponseDTO> getRecentPublications();

    List<VisualArtResponseDTO> getVisualArts();

    List<Publication> findBySectionIgnoreCase(String section);

    List<Publication> findByShowInHomeTrue();

    List<Publication> findBySectionIgnoreCaseAndStatus(String section, String status);
}