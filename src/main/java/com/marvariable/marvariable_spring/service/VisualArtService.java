package com.marvariable.marvariable_spring.service;

import com.marvariable.marvariable_spring.dto.response.VisualArtResponseDTO;
import java.util.List;

public interface VisualArtService {
    List<VisualArtResponseDTO> getAllVisualArts();
}