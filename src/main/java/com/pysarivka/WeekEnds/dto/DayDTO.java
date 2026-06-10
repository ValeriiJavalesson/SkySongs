package com.pysarivka.WeekEnds.dto;

import java.util.List;

public class DayDTO {

    private Long id;
    private String dateName; // Назва дня або дата (наприклад, "День 1" або "25.10.2026")
    private List<LocationDTO> locations; // Список локацій, що належать цьому дню

    public DayDTO() {
    }

    // Конструктор для виведення списку днів (без глибокої вкладеності локацій)
    public DayDTO(Long id, String dateName) {
        this.id = id;
        this.dateName = dateName;
    }

    // Повний конструктор
    public DayDTO(Long id, String dateName, List<LocationDTO> locations) {
        this.id = id;
        this.dateName = dateName;
        this.locations = locations;
    }

    // Геттери та Сеттери
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateName() {
        return dateName;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName;
    }

    public List<LocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDTO> locations) {
        this.locations = locations;
    }
}
