package com.marvariable.marvariable_spring.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visual_arts")
public class VisualArt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    private String category;

    @Column(name = "image_url")
    private String imageUrl;

    public VisualArt() {
    }

    public Long getId() {
        return id;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}