package com.marvariable.marvariable_spring.repository;

import com.marvariable.marvariable_spring.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findByCategoryIgnoreCase(String category);
}