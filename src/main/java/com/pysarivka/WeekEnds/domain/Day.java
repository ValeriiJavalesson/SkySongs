package com.pysarivka.WeekEnds.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "days") // Безпечна назва для MySQL
@Getter
@Setter // Використовуємо окремо замість @Data, щоб захистити equals/hashCode
public class Day implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT суто для MySQL
    private Long id;

    @Column(unique = true)
    private String number;

    @Column
    private String title;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Location> locations = new ArrayList<>();

    // Ваш логер працюватиме ідеально і без рекурсії
    @Override
    public String toString() {
        return "Day [id=" + id + ", number=" + number + ", title=" + title + "]";
    }   
}
