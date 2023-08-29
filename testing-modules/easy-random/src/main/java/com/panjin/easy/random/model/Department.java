package com.panjin.easy.random.model;

import java.util.StringJoiner;

/**
 * @author panjin
 */
public class Department {
    private final String depName;

    public Department(String depName) {
        this.depName = depName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Department.class.getSimpleName() + "[", "]").add("depName='" + depName + "'")
            .toString();
    }
}
