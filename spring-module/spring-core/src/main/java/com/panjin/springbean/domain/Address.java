package com.panjin.springbean.domain;

import lombok.Data;

/**
 * @author panjin
 */
@Data
public class Address {
    private String street;
    private int number;

    public Address(String street, int number) {
        this.street = street;
        this.number = number;
    }
}
