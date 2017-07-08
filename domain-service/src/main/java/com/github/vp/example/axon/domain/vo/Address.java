package com.github.vp.example.axon.domain.vo;

import java.io.Serializable;

/**
 * Created by vimalpar on 01/07/17.
 */
public class Address implements Serializable {
    private String building;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zip;

    public Address(String building, String street, String city, String state, String country, String zip) {
        this.building = building;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return String.format("Address: %s, %s, %s, %s, %s, %s", building, street, city, state, country, zip);
    }
}
