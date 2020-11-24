package com.quanli.model;

import javax.persistence.*;


@Entity
    @Table(name = "khachhang")
    public class Customer {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String firstName;
        private String lastName;
        private String address;
        private String image;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String address, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.image = image;
    }

    public Customer(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
}
