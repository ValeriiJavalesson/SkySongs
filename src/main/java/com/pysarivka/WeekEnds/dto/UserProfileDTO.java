package com.pysarivka.WeekEnds.dto;

public class UserProfileDTO {
    private String email;
    private String firstname;
    private String lastname;

    public UserProfileDTO(String email, String firstname, String lastname) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // Геттери та сеттери
    public String getEmail() { return email; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
}
