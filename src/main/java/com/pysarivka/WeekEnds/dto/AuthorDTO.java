package com.pysarivka.WeekEnds.dto;

public class AuthorDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private Integer icon; // Індекс або ID іконки для Android-додатка

    public AuthorDTO() {
    }

    public AuthorDTO(Long id, String firstname, String lastname, Integer icon) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.icon = icon;
    }

    // Геттери та Сеттери
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public Integer getIcon() { return icon; }
    public void setIcon(Integer icon) { this.icon = icon; }
}
