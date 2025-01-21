package com.bridgeLabz.bookStore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    private String area;
    private String city;
    private String state;
    private long zipcode;
}
