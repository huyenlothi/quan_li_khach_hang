package com.quanli.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;


public class CustomerForm {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private MultipartFile image;

    public CustomerForm() {
    }

    public CustomerForm(Long id, String firstName, String lastName, String address, MultipartFile image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.image = image;
    }

    public CustomerForm(String firstName, String lastName, String address, MultipartFile image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
