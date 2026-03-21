package com.marvariable.marvariable_spring.controller;

import com.marvariable.marvariable_spring.dto.response.VisualArtResponseDTO;
import com.marvariable.marvariable_spring.service.VisualArtService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visual-arts")
public class VisualArtController {

    private final VisualArtService visualArtService;

    public VisualArtController(VisualArtService visualArtService) {
        this.visualArtService = visualArtService;
    }

    @GetMapping
    public List<VisualArtResponseDTO> getAllVisualArts() {
        return visualArtService.getAllVisualArts();
    }
}