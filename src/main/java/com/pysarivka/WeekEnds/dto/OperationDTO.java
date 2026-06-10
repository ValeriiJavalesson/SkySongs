package com.pysarivka.WeekEnds.dto;

import java.util.List;

public class OperationDTO {

    private Long id;
    private String title;
    private String description;
    private String toolsAndMaterials;
    private Long locationId;
    private AuthorDTO author; // Додано поле для автора операції
    private List<OperationImageDTO> images;

    public OperationDTO() {
    }

    // Оновлений повний конструктор, який включає автора
    public OperationDTO(Long id, String title, String description, String toolsAndMaterials, Long locationId, AuthorDTO author, List<OperationImageDTO> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.toolsAndMaterials = toolsAndMaterials;
        this.locationId = locationId;
        this.author = author;
        this.images = images;
    }

    // Геттери та Сеттери
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getToolsAndMaterials() { return toolsAndMaterials; }
    public void setToolsAndMaterials(String toolsAndMaterials) { this.toolsAndMaterials = toolsAndMaterials; }

    public Long getLocationId() { return locationId; }
    public void setLocationId(Long locationId) { this.locationId = locationId; }

    // Нові геттер та сеттер для автора
    public AuthorDTO getAuthor() { return author; }
    public void setAuthor(AuthorDTO author) { this.author = author; }

    public List<OperationImageDTO> getImages() { return images; }
    public void setImages(List<OperationImageDTO> images) { this.images = images; }
}
