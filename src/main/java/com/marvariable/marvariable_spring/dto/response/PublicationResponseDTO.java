package com.marvariable.marvariable_spring.dto.response;

import java.time.LocalDate;

public record PublicationResponseDTO(
        Long id,
        String title,
        LocalDate publicationDate,
        String imageUrl) {

}
