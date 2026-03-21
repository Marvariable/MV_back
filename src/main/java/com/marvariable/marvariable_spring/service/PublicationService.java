package com.marvariable.marvariable_spring.service;

import com.marvariable.marvariable_spring.entity.Publication;
import java.util.List;

public interface PublicationService {

    List<Publication> getAllPublications();

    List<Publication> getPublicationsByCategory(String category);

    Publication save(Publication publication);
}