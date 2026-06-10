package com.pysarivka.WeekEnds.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "operation_images")
public class OperationImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Шлях до файлу на сервері або URL-посилання, якщо малюнки зберігаються у хмарі
    @Column(name = "image_path", nullable = false, length = 500)
    private String imagePath;

    // Зв'язок з Операцією: багато малюнків можуть належати одній операції
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_id", nullable = false)
    private Operation operation;

    // Обов'язковий конструктор для JPA
    public OperationImage() {
    }

    // Зручний конструктор
    public OperationImage(String imagePath, Operation operation) {
        this.imagePath = imagePath;
        this.operation = operation;
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

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
