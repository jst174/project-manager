package org.stepanenko.projectmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Country must not be empty")
    @Size(min = 2, max = 50, message = "Country must be between 2 and 50 characters long")
    private String country;
    @NotEmpty(message = "City must not be empty")
    @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters long")
    private String city;
    @NotEmpty(message = "Street must not be empty")
    @Size(min = 2, max = 50, message = "Street must be between 2 and 50 characters long")
    private String street;

    public Address() {
    }

    public Address(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
