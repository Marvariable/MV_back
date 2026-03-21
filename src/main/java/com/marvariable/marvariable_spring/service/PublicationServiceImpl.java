package com.marvariable.marvariable_spring.service;

import com.marvariable.marvariable_spring.entity.Publication;
import com.marvariable.marvariable_spring.repository.PublicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
}