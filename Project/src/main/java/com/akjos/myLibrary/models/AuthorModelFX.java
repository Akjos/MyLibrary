package com.akjos.myLibrary.models;

import com.akjos.myLibrary.tools.TypeFX;

import java.time.LocalDate;

public class AuthorModelFX extends ModelFX {

    private int id;
    private String name;
    private String surname;
    private String nickname;
    private LocalDate dob;
    private String nationality;

    public AuthorModelFX() {
        super(TypeFX.Author);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getDob() { return dob; }

    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) { this.nationality = nationality; }

    @Override
    public String toString() {
        return getName() + " " + getSurname();

    }
}
