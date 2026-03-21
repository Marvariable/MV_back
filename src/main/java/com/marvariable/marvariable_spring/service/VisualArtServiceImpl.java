package com.marvariable.marvariable_spring.service;

import com.marvariable.marvariable_spring.dto.response.VisualArtResponseDTO;
import com.marvariable.marvariable_spring.repository.VisualArtRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisualArtServiceImpl implements VisualArtService {

    private final VisualArtRepository visualArtRepository;

    public VisualArtServiceImpl(VisualArtRepository visualArtRepository) {
        this.visualArtRepository = visualArtRepository;
    }

    @Override
    public List<VisualArtResponseDTO> getAllVisualArts() {
        return visualArtRepository.findAll()
                .stream()
                .map(visualArt -> new VisualArtResponseDTO(
                        visualArt.getId(),
                        visualArt.getImageUrl(),
                        visualArt.getPublicationDate(),
                        visualArt.getCategory()
                ))
                .toList();
    }
}