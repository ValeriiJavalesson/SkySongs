package com.pysarivka.WeekEnds.dto;

public class LocationDTO {

    private Long id;
    private String name;
    private Long dayId; // Замість повного об'єкта Day передаємо лише його ID

    public LocationDTO() {
    }

    public LocationDTO(Long id, String name, Long dayId) {
        this.id = id;
        this.name = name;
        this.dayId = dayId;
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

    public Long getDayId() {
        return dayId;
    }

    public void setDayId(Long dayId) {
        this.dayId = dayId;
    }
}
