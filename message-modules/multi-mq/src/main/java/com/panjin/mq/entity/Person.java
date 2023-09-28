package com.panjin.mq.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author panjin
 */
@Data
public class Person {

    private String name;
    private Integer age;
    private String address;
    private Date birthday;
}
