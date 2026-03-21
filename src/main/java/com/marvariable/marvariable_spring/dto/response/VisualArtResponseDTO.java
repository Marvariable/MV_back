package com.marvariable.marvariable_spring.dto.response;

import java.time.LocalDate;

public record VisualArtResponseDTO(
        Long id,
        String imageUrl,
        LocalDate publicationDate,
        String category
) {
}