package com.pysarivka.WeekEnds.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "tools_and_materials", length = 1000)
    private String toolsAndMaterials;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    // Нове поле: зв'язок з автором (користувачем)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @OneToMany(mappedBy = "operation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OperationImage> images = new ArrayList<>();

    public Operation() {
    }

    // Оновлений конструктор
    public Operation(String title, String description, String toolsAndMaterials, Location location, User author) {
        this.title = title;
        this.description = description;
        this.toolsAndMaterials = toolsAndMaterials;
        this.location = location;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToolsAndMaterials() {
        return toolsAndMaterials;
    }

    public void setToolsAndMaterials(String toolsAndMaterials) {
        this.toolsAndMaterials = toolsAndMaterials;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // Геттер та сеттер для автора
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<OperationImage> getImages() {
        return images;
    }

    public void setImages(List<OperationImage> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Operation [id=" + id + ", title=" + title + ", author=" + (author != null ? author.getId() : "null") + "]";
    }
}
