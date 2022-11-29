package com.panjin.springbean.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author panjin
 */
@Data
@Component
public class Company {
    private Address address;

    public Company(Address address) {
        this.address = address;
    }
}
