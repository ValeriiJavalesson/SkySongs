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
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // Зв'язок з Днем: багато локацій належать одному дню
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id", nullable = false)
    private Day day;

    // Зв'язок з Операціями: одна локація містить список операцій
    // cascade = CascadeType.ALL означає, що при видаленні локації автоматично видаляться всі її операції
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operation> operations = new ArrayList<>();

    // Конструктор за замовчуванням (обов'язковий для JPA)
    public Location() {
    }

    // Конструктор для зручного створення об'єкта
    public Location(String name, Day day) {
        this.name = name;
        this.day = day;
    }

    // Геттери та Сеттери
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

	@Override
	public String toString() {
		 return "Location [id=" + id + ", name=" + name + "]";
	}
    
}
