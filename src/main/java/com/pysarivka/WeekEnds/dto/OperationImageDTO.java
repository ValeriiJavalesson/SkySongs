package com.pysarivka.WeekEnds.dto;

public class OperationImageDTO {

    private Long id;
    private String imagePath;

    public OperationImageDTO() {
    }

    public OperationImageDTO(Long id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    // Геттери та Сеттери
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
