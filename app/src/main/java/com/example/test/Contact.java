package com.example.test;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String surname;
    private String fullname;
    private String mail;
    private String photo;
    private int age;

    public Contact(String name, String surname, String fullname, String mail, String photo, int age) {
        this.name = name;
        this.surname = surname;
        this.fullname = fullname;
        this.mail = mail;
        this.photo = photo;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullname() {
        return fullname;
    }

    public String getMail() {
        return mail;
    }

    public String getPhoto() {
        return photo;
    }

    public int getAge() {
        return age;
    }


}
